package model;

/*
 * @author	Nick Foster
 * Adapted from code by Ariana Fairbanks
 * 
 */

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import javax.swing.JOptionPane;

import adapter.Controller;
import milieu.Address;
import milieu.Planetoid;
import milieu.Star;
import milieu.StarSystem;
import milieu.World;
import rules.Dice;

public class SQLiteData {
	private static final String[] TABLE_NAMES = { "STAR", "WORLD", "STARSYSTEM", "SPACEPORT", "ECONOMY" };

	private Controller controller;
	private static Connection connection;
	public static boolean hasData;

	/*
	 * CONSTRUCTORS
	 */
	public SQLiteData(Controller controller) {
		this.controller = controller;

		hasData = false;
		connect();
		databaseSetup();
	}

	/*
	 * PUBLIC METHODS
	 */
	public int getLastStarIndex() throws SQLException {
		return getLastIndex("id", "STAR");
	}

	public int getLastWorldIndex() throws SQLException {
		return getLastIndex("id", "WORLD");
	}

	private int getLastIndex(String column, String table) throws SQLException {
		String string = String.format("SELECT %s FROM %s ORDER BY %s DESC LIMIT 1;", column, table, column);
		PreparedStatement statement = connection.prepareStatement(string);

		ResultSet results;
		int index = 0;
		try {
			results = statement.executeQuery(string);
			index = results.getInt(column);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return index;
	}

	public boolean addSubsector(int sector, int subsector) throws SQLException {
		boolean add = false;

		if (connection == null)
			connect();

		int starIndex = getLastStarIndex();
		int worldIndex = getLastWorldIndex();

		try {
			connection.setAutoCommit(false);
			StarSystem starSystem;

			for (int i = 0; i < 80; ++i) {
				if (Dice.roll(2) == 2) {
					starSystem = new StarSystem(sector, subsector, i);

					for (Iterator<Star> it = starSystem.starList().iterator(); it.hasNext();) {
						/*
						 * TODO - contains entire statement thing from addStar
						 * 
						 */

					}

					// listWorlds() doesn't return RINGS or GAS GIANTS
					for (Iterator<Planetoid> it = starSystem.listWorlds().iterator(); it.hasNext();) {
						/*
						 * TODO - contains entire statement thing from addWorld
						 * 
						 */

					}
				}

				connection.commit();
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			connection.setAutoCommit(true);

		}

		return add;
	}

	public boolean addStar(int suppliedIndex, Star star) {
		boolean add = false;

		if (connection == null)
			connect();

		int starIndex;
		if (star.isPersistent())
			starIndex = star.getIndex();
		else
			starIndex = suppliedIndex;

		PreparedStatement statement;
		String string = String.format("INSERT INTO %s VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ? );", TABLE_NAMES[0]);
		try {
			// FIXME - this performs insert
			int pos = 1;
			statement = connection.prepareStatement(string);

			// address (doesn't use sub-orbit)
			statement.setString(pos++, String.valueOf(starIndex));
			statement.setString(pos++, String.valueOf(star.sector()));
			statement.setString(pos++, String.valueOf(star.subsector()));
			statement.setString(pos++, String.valueOf(star.cluster()));
			statement.setString(pos++, String.valueOf(star.orbit()));

			// attributes
			statement.setString(pos++, star.getName());
			statement.setString(pos++, String.valueOf(star.getSize()));
			statement.setString(pos++, String.valueOf(star.getColor()));
			statement.setString(pos++, String.valueOf(star.maxOrbits()));

			statement.execute();
			add = true;

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(Controller.errorPanel, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);

		}

		return add;
	}

	public boolean addWorld(int suppliedIndex, int starsystem, World world) {
		boolean add = false;

		if (connection == null)
			connect();

		int worldIndex;
		if (world.isPersistent())
			worldIndex = world.getIndex();
		else
			worldIndex = suppliedIndex;

		try {
			PreparedStatement statement;
			int pos = 1;

			statement = connection
					.prepareStatement("INSERT INTO WORLD VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );");

			// cast world as "address" to access these methods
			// statement.setString(pos++, String.valueOf(((Address) world).sector()));
			// statement.setString(pos++, String.valueOf(((Address) world).subsector()));
			// statement.setString(pos++, String.valueOf(((Address) world).cluster()));
			// statement.setString(pos++, String.valueOf(((Address) world).orbit()));
			// statement.setString(pos++, String.valueOf(((Address) world).suborbit()));

			statement.setString(pos++, String.valueOf(worldIndex));
			// cast world as "planetoid" to access these methods
//			statement.setString(pos++, String.valueOf(((Planetoid) world).starsystem()));
			statement.setString(pos++, String.valueOf(((Planetoid) world).orbit()));
			statement.setString(pos++, String.valueOf(((Planetoid) world).suborbit()));

			// attributes
			statement.setString(pos++, String.valueOf(world.getName()));

			statement.setString(pos++, String.valueOf(world.getSize()));
			statement.setString(pos++, String.valueOf(world.getAtmosphere()));
			statement.setString(pos++, String.valueOf(world.getHydrosphere()));
			statement.setString(pos++, String.valueOf(world.getPopulation()));

			//
			statement.setString(pos++, String.valueOf(world.getGovernment()));
			statement.setString(pos++, String.valueOf(world.getLawLevel()));
			statement.setString(pos++, String.valueOf(world.getTechLevel()));
			statement.setString(pos++, String.valueOf(world.getSpaceport()));

			statement.execute();
			add = true;

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(Controller.errorPanel, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);

		}

		return add;
	}

	/*
	 * GETTERS
	 */
	public ResultSet getStarData() {
		ResultSet results = null;

		PreparedStatement statement;
		String query;
		if (connection == null)
			connect();

		try {
			// TODO
			query = String.format("SELECT * FROM %s ORDER BY %s", TABLE_NAMES[0], "id");
			statement = connection.prepareStatement(query);
			results = statement.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return results;
	}

	/*
	 * PRIVATE METHODS
	 */
	private boolean starExists(int index) {
		return recordExists(index, TABLE_NAMES[0]);
	}

	private boolean worldExists(int index) {
		return recordExists(index, TABLE_NAMES[1]);
	}

	private boolean recordExists(int index, String table) {
		boolean recordExists = false;
		ResultSet set = null;

		try {
			if (connection == null)
				connect();

			String query = "SELECT * FROM " + table + " WHERE index = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, String.valueOf(index));

			set = statement.executeQuery();
			recordExists = set.next();

		} catch (SQLException e) {

		}

		return recordExists;
	}

	private void connect() {
		String filename = "Project.db";

		try {
			Class.forName("org.sqlite.JDBC");
			String databaseFilePath = "jdbc:sqlite:C:/ProgramData/" + filename;

			connection = DriverManager.getConnection(databaseFilePath);

		} catch (SQLException | ClassNotFoundException e) {
			// currently only configured for Linux/Windows

			try {
				Class.forName("org.sqlite.JDBC");
				File homedir = new File(System.getProperty("user.home/"));
				String databaseFilePath = "jdbc:sqlite:" + homedir + filename;

				connection = DriverManager.getConnection(databaseFilePath);

			} catch (SQLException | ClassNotFoundException e2) {
				e2.printStackTrace();
				System.out.println("This only works for PC and Linux.");

			}
		}

		databaseSetup();
	}

	private void databaseSetup() {
		if (!hasData) {
			hasData = true;

			Statement statement;
			String string;
			String tableName, key, address, data;
			String i = "INTEGER(2)";

			try {
				statement = connection.createStatement();

				// STARS table setup
				string = "SELECT name FROM sqlite_master WHERE type='table' AND name='STAR'";
				if (!statement.executeQuery(string).next()) {
					statement = connection.createStatement();

					tableName = "STAR";
					key = "star_num INTEGER(8) PRIMARY KEY";
					address = String.format("star_system INTEGER(8), orbit %s", i);
					data = String.format("name CHAR(40), size CHAR(1), color CHAR(1), maxOrbits %s", i);

					string = String.format("CREATE TABLE %s (%s, %s, %s);", tableName, key, address, data);
					statement.executeUpdate(string);

				}

				// WORLDS table setup
				string = "SELECT name FROM sqlite_master WHERE type='table' AND name='WORLD'";
				if (!statement.executeQuery(string).next()) {
					statement = connection.createStatement();

					string = "CREATE TABLE WORLD(world_num INTEGER PRIMARY KEY, "
							// address
							+ "sector INTEGER, " + "subsector INTEGER(2), " //
							+ "cluster INTEGER, " + "orbit INTEGER(2), " + "suborbit INTEGER(2), "
							//
							+ "name CHAR(40), "
							//
							+ "size INTEGER(2), " + "atmosphere INTEGER(2), " + "hydrosphere INTEGER(2), "
							+ "population INTEGER(2), "
							//
							+ "government INTEGER(2), " + "law INTEGER(2), " + "techLevel INTEGER(2), "
							+ "spaceport CHAR(1) " + ");";
					statement.executeUpdate(string);
				}

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
	}

}

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
import milieu.WorldType;
import rules.Dice;

public class SQLiteData {
	private static final int SYSTEMS_PER_SUBSECTOR = 80;

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

	public int getLastSystemIndex() throws SQLException {
		return getLastIndex("id", "STARSYSTEM");
	}

	public int getLastWorldIndex() throws SQLException {
		return getLastIndex("id", "WORLD");
	}

	private int getLastIndex(String column, String table) throws SQLException {
		String string = String.format("SELECT %s FROM %s ORDER BY %s DESC LIMIT 1;", column, table, column);
		PreparedStatement statement = connection.prepareStatement(string);

		int index = 0;
		ResultSet results = statement.executeQuery(string);
		if (results.next()) {
			index = results.getInt(column);
		}

		return index;
	}

	public boolean addSubsector(int sector) {
		boolean add = false;

		if (connection == null)
			connect();

		int systemIndex = 0;
		int starIndex = 0;
		int worldIndex = 0;

		try {
			// connection.setAutoCommit(false);
			StarSystem starSystem;
			PreparedStatement statement;

			for (int i = 0; i < SYSTEMS_PER_SUBSECTOR; ++i) {
				int pos;
				if (Dice.roll(2) == 2) {
					pos = 1;
					starSystem = new StarSystem(sector, i, systemIndex);

					String string = String.format("INSERT INTO STARSYSTEM VALUES( ?, ?, ? );");
					statement = connection.prepareStatement(string);

					//
					statement.setString(pos++, String.valueOf(systemIndex));
					statement.setString(pos++, String.valueOf(sector));
					statement.setString(pos++, String.valueOf(i));
					statement.execute();

					/*
					 * INSERT STARS
					 */
					statement = connection.prepareStatement("INSERT INTO STAR VALUES( ?, ?, ?, ?, ?, ?, ? );");
					for (Iterator<Star> it = starSystem.starList().iterator(); it.hasNext();) {
						pos = 1;
						Star star = it.next();

						try {
							statement.setString(pos++, String.valueOf(starIndex));
							statement.setString(pos++, String.valueOf(systemIndex));

							statement.setString(pos++, String.valueOf(star.orbit()));
							statement.setString(pos++, star.getName());
							statement.setString(pos++, String.valueOf(star.getSize()));
							statement.setString(pos++, String.valueOf(star.getColor()));
							statement.setString(pos++, String.valueOf(star.maxOrbits()));
							statement.execute();

						} catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(Controller.errorPanel, "Invalid input.", "Error",
									JOptionPane.ERROR_MESSAGE);

						}

						// last step
						++starIndex;
					}

					statement = connection
							.prepareStatement("INSERT INTO WORLD VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );");
					for (Iterator<Planetoid> it = starSystem.getSpaceObjects().iterator(); it.hasNext();) {
						pos = 1;
						World world = it.next();

						try {
							statement.setString(pos++, String.valueOf(worldIndex));
							statement.setString(pos++, String.valueOf(systemIndex));

							statement.setString(pos++, String.valueOf(((Planetoid) world).orbit()));
							statement.setString(pos++, String.valueOf(world.getName()));
							statement.setString(pos++, String.valueOf(WorldType.typeIndex(world.getType())));

							statement.setString(pos++, String.valueOf(world.getSize()));
							statement.setString(pos++, String.valueOf(world.getAtmosphere()));
							statement.setString(pos++, String.valueOf(world.getHydrosphere()));
							statement.setString(pos++, String.valueOf(world.getPopulation()));
							statement.setString(pos++, String.valueOf(world.getGovernment()));
							statement.setString(pos++, String.valueOf(world.getLawLevel()));

							//
							statement.setString(pos++, String.valueOf(world.getTechLevel()));
							statement.execute();
						} catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(Controller.errorPanel, "Invalid input.", "Error",
									JOptionPane.ERROR_MESSAGE);

						}

						// last step
						++worldIndex;
					}
					// connection.commit();
					++systemIndex;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return add;
	}

	public boolean insertStarSystem(int ssIndex, StarSystem ss) {
		boolean add = false;

		if (connection == null)
			connect();

		int systemIndex;
		if (ss.isPersistent())
			systemIndex = ss.getIndex();
		else
			systemIndex = 0;

		PreparedStatement statement;
		String string = String.format("INSERT INTO STARSYSTEM VALUES( ?, ?, ? );");
		try {
			// FIXME - this performs insert
			int pos = 1;
			statement = connection.prepareStatement(string);

			// address (doesn't use sub-orbit)
			statement.setString(pos++, String.valueOf(systemIndex));

			// attributes
			// TODO

			statement.execute();
			add = true;

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(Controller.errorPanel, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);

		}

		return add;
	}

	public boolean insertStar(int starsystem, Star star) {
		boolean add = false;

		if (connection == null)
			connect();

		int starIndex;
		if (star.isPersistent())
			starIndex = star.getIndex();
		else
			starIndex = 0;

		PreparedStatement statement;
		String string = String.format("INSERT INTO STAR VALUES( ?, ?, ?, ?, ?, ?, ? );");
		try {
			// FIXME - this performs insert
			int pos = 1;
			statement = connection.prepareStatement(string);

			// address (doesn't use sub-orbit)
			statement.setString(pos++, String.valueOf(starIndex));
			statement.setString(pos++, String.valueOf(starsystem));
			// statement.setString(pos++, String.valueOf(star.starsystem()));
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

	public boolean insertWorld(int starsystem, World world) {
		boolean add = false;

		if (connection == null)
			connect();

		int worldIndex;
		if (world.isPersistent())
			worldIndex = world.getIndex();
		else
			worldIndex = 0;

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
			// statement.setString(pos++, String.valueOf(((Planetoid) world).starsystem()));
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
			query = String.format("SELECT * FROM STAR ORDER BY id");
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
		return recordExists(index, "STAR");
	}

	private boolean worldExists(int index) {
		return recordExists(index, "WORLD");
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

	/*
	 * CONNECT TO DATABASE
	 * 
	 */
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
				Statement statement = connection.createStatement();
				ResultSet test = statement.executeQuery("PRAGMA foreign_keys = ON;");

				if (test == null)
					System.out.println("Null");
				else
					System.out.println("Not null");

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
			String tableName, key, address, data, fk;

			try {
				statement = connection.createStatement();

				// STARSYSTEM table setup
				string = "SELECT name FROM sqlite_master WHERE type='table' AND name='STARSYSTEM'";
				if (!statement.executeQuery(string).next()) {
					statement = connection.createStatement();

					tableName = "STARSYSTEM";
					key = "id INTEGER PRIMARY KEY";
					address = String.format("sector INTEGER, subsector INTEGER");
					// data = String.format("name CHAR, size CHAR, color CHAR, maxOrbits %s");

					string = String.format("CREATE TABLE %s (%s, %s);", tableName, key, address);
					statement.executeUpdate(string);

				}

				// ECONOMY table setup
				string = "SELECT name FROM sqlite_master WHERE type='table' AND name='ECONOMY'";
				if (!statement.executeQuery(string).next()) {
					statement = connection.createStatement();

					tableName = "ECONOMY";
					key = "id INTEGER PRIMARY KEY";
					data = String.format("resources INTEGER, labor INTEGER, infrastructure INTEGER, culture INTEGER");

					string = String.format("CREATE TABLE %s (%s, %s);", tableName, key, data);
					statement.executeUpdate(string);

				}

				// STAR table setup
				string = "SELECT name FROM sqlite_master WHERE type='table' AND name='STAR'";
				if (!statement.executeQuery(string).next()) {
					statement = connection.createStatement();

					tableName = "STAR";
					key = "id INTEGER PRIMARY KEY";
					address = "system_id INTEGER";

					data = "orbit INTEGER, name VARCHAR, size VARCHAR, color VARCHAR, maxOrbits INTEGER";

					fk = "FOREIGN KEY (system_id) REFERENCES STARSYSTEM(id)";

					string = String.format("CREATE TABLE %s (%s, %s, %s, %s);", tableName, key, address, data, fk);
					statement.executeUpdate(string);
				}

				// WORLDTYPE table setup
				string = "SELECT name FROM sqlite_master WHERE type='table' AND name='WORLDTYPE'";
				if (!statement.executeQuery(string).next()) {
					statement = connection.createStatement();
					string = "CREATE TABLE WORLDTYPE(id INTEGER PRIMARY KEY, name VARCHAR);";
					statement.executeUpdate(string);
				}

				// WORLD table setup
				string = "SELECT name FROM sqlite_master WHERE type='table' AND name='WORLD'";
				if (!statement.executeQuery(string).next()) {
					statement = connection.createStatement();

					tableName = "WORLD";
					key = "id INTEGER PRIMARY KEY";
					address = "system_id INTEGER";

					//
					data = "orbit INTEGER, name VARCHAR, world_type INTEGER";
					data += ", size INTEGER, atmosphere INTEGER, hydrosphere INTEGER, population INTEGER, government INTEGER, lawLevel INTEGER";
					data += ", techLevel INTEGER";

					//
					fk = "FOREIGN KEY (system_id) REFERENCES STARSYSTEM(id)";
					// fk += ", FOREIGN KEY (world_type) REFERENCES WORLDTYPE(id)";

					string = String.format("CREATE TABLE %s (%s, %s, %s, %s);", tableName, key, address, data, fk);
					statement.executeUpdate(string);
				}

				// SPACEPORT table setup
				string = "SELECT name FROM sqlite_master WHERE type='table' AND name='SPACEPORT'";
				if (!statement.executeQuery(string).next()) {
					statement = connection.createStatement();

					tableName = "SPACEPORT";
					key = "id INTEGER PRIMARY KEY";
					address = "system_id INTEGER";

					//
					data = "world_id INTEGER, class VARCHAR, docking_fee INTEGER";

					//
					fk = "FOREIGN KEY (system_id) REFERENCES STARSYSTEM(id)";
					fk += ", FOREIGN KEY (world_id) REFERENCES WORLD(id)";

					string = String.format("CREATE TABLE %s (%s, %s, %s);", tableName, key, address, data);

					statement.executeUpdate(string);
				}

				// WORLD_ECONOMY table setup
				string = "SELECT name FROM sqlite_master WHERE type='table' AND name='WORLD_ECONOMY'";
				if (!statement.executeQuery(string).next()) {
					statement = connection.createStatement();

					tableName = "WORLD_ECONOMY";
					key = "id INTEGER PRIMARY KEY";
					data = String.format("resources INTEGER, labor INTEGER, infrastructure INTEGER, culture INTEGER");

					string = String.format("CREATE TABLE %s (%s, %s);", tableName, key, data);
					statement.executeUpdate(string);

				}

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
	}

}

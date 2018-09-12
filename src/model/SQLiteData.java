package model;

/*
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

import javax.swing.JOptionPane;

import adapter.Controller;
import milieu.Star;
import milieu.World;

public class SQLiteData {
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
	public boolean addStar(Star star) {
		boolean add = false;

		if (connection == null)
			connect();

		try {
			if (star.isPersistent()) {
				// FIXME - this should perform update instead of insert
				JOptionPane.showMessageDialog(Controller.errorPanel, "Star already exists.", "Error",
						JOptionPane.ERROR_MESSAGE);

			} else {
				// FIXME - this performs insert
				PreparedStatement statement;
				int pos = 1;

				statement = connection.prepareStatement("INSERT INTO STARS VALUES( ?, ?, ?, ? );");
				// statement.setString(pos++, "star_num");
				statement.setString(pos++, "Name");
				statement.setString(pos++, "Orbit");
				statement.setString(pos++, "Size");
				statement.setString(pos++, "Color");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(Controller.errorPanel, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);

		}

		return add;
	}

	public boolean addWorld(World world) {
		boolean add = false;

		if (connection == null)
			connect();

		try {
			if (worldExists(world.getIndex())) {
				JOptionPane.showMessageDialog(Controller.errorPanel, "World already exists.", "Error",
						JOptionPane.ERROR_MESSAGE);

			} else {
				PreparedStatement statement;
				int pos = 1;

				statement = connection.prepareStatement("INSERT INTO WORLDS VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
				statement.setString(pos++, String.valueOf(world.getIndex()));
				statement.setString(pos++, String.valueOf(world.getName()));
				statement.setString(pos++, String.valueOf(world.getOrbit()));
				statement.setString(pos++, String.valueOf(world.getSubOrbit()));

				// scores
				statement.setString(pos++, String.valueOf(world.getSize()));
				statement.setString(pos++, String.valueOf(world.getAtmosphere()));
				statement.setString(pos++, String.valueOf(world.getHydrosphere()));
				statement.setString(pos++, String.valueOf(world.getPopulation()));
				statement.setString(pos++, String.valueOf(world.getGovernment()));
				statement.setString(pos++, String.valueOf(world.getLawLevel()));

				statement.execute();
				add = true;

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(Controller.errorPanel, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);

		}

		return add;
	}

	/*
	 * PRIVATE METHODS
	 */
	private boolean starExists(int index) {
		return recordExists(index, "STARS");
	}

	private boolean worldExists(int index) {
		return recordExists(index, "WORLDS");
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

			try {
				statement = connection.createStatement();

				// WORLDS table setup
				string = "SELECT name FROM sqlite_master WHERE type='table' AND name='WORLDS'";
				if (!statement.executeQuery(string).next()) {
					statement = connection.createStatement();

					string = "CREATE TABLE WORLDS(world_num INTEGER PRIMARY KEY, " + "name CHAR(40), "
							+ "orbit INTEGER(2), " + "suborbit INTEGER(2), " + "size INTEGER(2), "
							+ "atmosphere INTEGER(2), " + "hydrosphere INTEGER(2), " + "population INTEGER(2), "
							+ "government INTEGER(2), " + "law INTEGER(2) " + ");";
					// + "PRIMARY KEY (world_num));";
					statement.executeUpdate(string);
				}

				// STARS table setup
				string = "SELECT name FROM sqlite_master WHERE type='table' AND name='STARS'";
				if (!statement.executeQuery(string).next()) {
					statement = connection.createStatement();
					string = "CREATE TABLE STARS(star_num INTEGER PRIMARY KEY, " + "name CHAR(40), "
							+ "orbit INTEGER(2), " + "size CHAR(2), " + "color CHAR(2) " + ");";
					// + "PRIMARY KEY (star_num));";
					statement.executeUpdate(string);

				}

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
	}

}

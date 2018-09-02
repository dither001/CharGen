package model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import adapter.Controller;

public class SQLiteData {
	private Controller controller;
	private static Connection connection;
	public static boolean hasData;

	public SQLiteData(Controller controller) {
		this.controller = controller;

		hasData = false;
		connect();
		databaseSetup();
	}

	/*
	 * PRIVATE METHODS
	 */
	private void connect() {
		// currently only configured for Linux/Windows

		try {
			Class.forName("org.sqlite.JDBC");
			String databaseFilePath = "jdbc:sqlite:C:/ProgramData/Project";

			connection = DriverManager.getConnection(databaseFilePath);

		} catch (SQLException | ClassNotFoundException e) {

			try {
				Class.forName("org.sqlite.JDBC");
				File homedir = new File(System.getProperty("user.home"));
				String databaseFilePath = "jdbc:sqlite:" + homedir + "/Project";

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

			String string;
			Statement state;

			try {
				state = connection.createStatement();

				string = "SELECT name FROM sqlite_master WHERE type='table' AND name='MEMBERDATA'";
				ResultSet res = state.executeQuery(string);

				if (!res.next()) {
					state = connection.createStatement();

					// table setup
					string = "CREATE TABLE MEMBERDATA(lastName VARCHAR(20)," + "firstName VARCHAR(20),"
							+ "SCAName VARCHAR(20)," + "membershipNumber INTEGER," + "expirationDate VARCHAR(10),"
							+ "isAnAdult BOOLEAN," + "PRIMARY KEY (lastName, firstName));";
					state.executeUpdate(string);

					// table2 setup
					string = "CREATE TABLE ATTENDANCEDATA(lastName VARCHAR(20)," + "firstName VARCHAR(20),"
							+ "isAnAdult BOOLEAN," + "isMember BOOLEAN," + "hadFeast BOOLEAN,"
							+ "PRIMARY KEY (lastName, firstName));";
					state.executeUpdate(string);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

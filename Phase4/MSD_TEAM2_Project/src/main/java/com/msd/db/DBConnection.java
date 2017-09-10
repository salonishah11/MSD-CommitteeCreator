package com.msd.db;

import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class DBConnection.
 */
//DBConnection class to generate and establish a connection to the MySQL database using JDBC
public class DBConnection {

	/** The db conn inst. */
	private static DBConnection dbConnInst = new DBConnection();
	
	/** The conn. */
	private static Connection conn;
	
	/** The config file. */
	private static InputStream configFile;

	/**
	 * Instantiates a new DB connection.
	 */
	private DBConnection() {
		configFile = this.getClass().getResourceAsStream("db.Properties");
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public static Connection getConnection() {
		Properties config = new Properties();

		if (conn == null) {
			try {
				config.load(configFile);
				String url = config.getProperty("jdbc.url");
				String user = config.getProperty("jdbc.username");
				String password = config.getProperty("jdbc.password");
				conn = getSpecificConnection(url, user, password);
				Statement stmnt = conn.createStatement();
				stmnt.executeUpdate("SET sql_mode=''");

			} catch (Exception e) {
				conn = getfallbackDBConnection();
			}
		}
		return conn;
	}

	/**
	 * Gets the fallback DB connection.
	 *
	 * @return the fallback DB connection
	 */
	public static Connection getfallbackDBConnection() {
		try {
			String user = "appuser";
			String password = "pass123";
			String url = "jdbc:mysql://ec2-184-73-110-234.compute-1.amazonaws.com:3306/dblp_full";
			conn = getSpecificConnection(url, user, password);
			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate("SET sql_mode=''");
		} catch (Exception e) {
			System.out.println("fallback also failed");
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * Gets the specific connection.
	 *
	 * @param url the url
	 * @param user the user
	 * @param password the password
	 * @return the specific connection
	 */
	public static Connection getSpecificConnection(String url, String user, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("JDBC connection to mysql failed with error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Failed with some exception while getconnection " + e.getMessage());
		}
		return null;
	}

}

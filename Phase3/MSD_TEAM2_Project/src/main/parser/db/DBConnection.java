package main.parser.db;

import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

//DBConnection class to generate and establish a connection to the MySQL database using JDBC
public class DBConnection {

	private static DBConnection dbConnInst = new DBConnection();
	private static Connection conn;
	private static InputStream configFile;

	private DBConnection() {
		configFile = this.getClass().getResourceAsStream("db.Properties");
	}

	/*
	 * public static DBConnection getInstance() { if (dbConnInst == null) {
	 * dbConnInst = new DBConnection(); } return dbConnInst; }
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
				System.out.println("Failed with some exception while getconnection " + e.getMessage());
			}
		}
		return conn;
	}

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

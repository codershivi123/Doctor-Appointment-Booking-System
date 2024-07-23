package com.mediSlot.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static DBConnection dbConnection;
	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String username = "MediSlotUser";
	private final String password = "medislot";
	private Connection connection;

	private DBConnection() {
		super();
	}

	public static DBConnection getDbConnection() {
		if (dbConnection == null) {
			synchronized (DBConnection.class) {
				if (dbConnection == null) {
					dbConnection = new DBConnection();
				}
			}

		}

		return dbConnection;
	}

	public Connection getConnection() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			if (connection == null) {
				 
				connection = DriverManager.getConnection(url, username, password);
				if (!connection.isClosed()) {
					return connection;
				}
			}
		}
		 catch (ClassNotFoundException e) {
			System.out.println("class");
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public boolean closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
		}
		return connection.isClosed();
	}

}

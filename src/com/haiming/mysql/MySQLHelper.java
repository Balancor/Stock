package com.haiming.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLHelper {
	public static final String BASE_URL = "jdbc:mysql://127.0.0.1/";
	public static final String NAME = "com.mysql.jdbc.Driver";
	public static final String USER = "root";
	public static final String PASSWORD = "guoguo";

	public Connection mConnection = null;
	public PreparedStatement mPreparedStatement = null;

	public MySQLHelper(String databaseName) {
		try {
			Class.forName(NAME);
			mConnection = DriverManager.getConnection(BASE_URL + databaseName,
					USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet execute(String sql) {
		ResultSet resultSet = null;
		try {
			mPreparedStatement = mConnection.prepareStatement(sql);
			resultSet = mPreparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

}

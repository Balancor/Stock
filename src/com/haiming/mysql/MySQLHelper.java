package com.haiming.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLHelper {
	private static final String BASE_URL = "jdbc:mysql://127.0.0.1/";
	private static final String NAME = "com.mysql.jdbc.Driver";
	private static final String USER = "root";
	private static final String PASSWORD = "guoguo";
	
	private static final String DEFAULT_DATABASE = "Stock";
	private static final String DEFAULT_TABLE = "Company";

	public Connection mConnection = null;
	public PreparedStatement mPreparedStatement = null;

	public MySQLHelper(String databaseName) {
		init(databaseName);
	}
	
	private void init(String databaseName){
		if(databaseName == null) databaseName = DEFAULT_DATABASE;
		try {
			Class.forName(NAME);
			mConnection = DriverManager.getConnection(BASE_URL + databaseName,
					USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
		if(mConnection == null){
			init(null);
		}
		return mConnection;
			
	}
	
	public MySQLHelper(){
		this(null);
	}

	public int executeUpdate(String sql, String[] values) {
		int ret = 0;
		try {
			mPreparedStatement = mConnection.prepareStatement(sql);
			mPreparedStatement.setString(1, values[0]);
			mPreparedStatement.setString(2, values[1]);
			mPreparedStatement.setInt(3, Integer.parseInt(values[2]));
			ret = mPreparedStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return ret;
	}

}

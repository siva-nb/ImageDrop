package edu.uta.imagedrop.mysql.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.uta.imagedrop.constants.DBConstants;


public class DBConnectionManager {
	Connection connection = null;
	
	public Connection getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DBConstants.MYSQL_URI,DBConstants.USER, DBConstants.PASSWORD);
			return connection;
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeConnection(Connection connection) throws SQLException{
		connection.close();
	}
	
}

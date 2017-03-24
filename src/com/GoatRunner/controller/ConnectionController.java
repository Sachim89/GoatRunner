package com.GoatRunner.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionController {
	static Connection createConnection(){
		
		
		Connection connection = null;
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//return;
			}
			
			try {
				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SID","username","password");
				if(connection != null){
					System.out.println("Connected to database");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error in connecting to database");
				e.printStackTrace();
			}
			return connection;
			//connection.close();
			
		}	
public static void main(String args[]){
	createConnection();
}
	
		
	}





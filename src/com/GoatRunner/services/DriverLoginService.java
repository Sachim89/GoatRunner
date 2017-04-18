package com.GoatRunner.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.Driver;


public class DriverLoginService {
	private static ConnectionService connection = new ConnectionService();


	public static Driver login_driver(int driver_id, String password) throws GoatRunnerException, SQLException {
		ConnectionService connection = new ConnectionService(); // class for connection

		Connection con = connection.createConnection();
		PreparedStatement st = con.prepareStatement("select * from driver where driverid=?");
		st.setInt(1, driver_id);

		ResultSet rs = st.executeQuery();
		String checkDriver = "";
		String checkpwd = "";
		if (rs != null) {
			while (rs.next()) {
				checkDriver = rs.getString(1);
				checkpwd = rs.getString(3);
			}
		}

		
		Driver driver = new Driver();

		// if driver exists and password is correct
		if (checkDriver.equalsIgnoreCase(Integer.toString(driver_id)) && checkpwd.equalsIgnoreCase(password)) {
			if (rs != null) {
				while (rs.next()) {
					driver.setDriver_id(driver_id);
					driver.setName(rs.getString(2));
					driver.setPhone_number(rs.getString(4));
				}
			}
			return driver;
			// successful
			
		}
		
		// to check if password is incorrect
		if (checkDriver.equals(driver_id) && !(checkpwd.equals(password))) {
			throw new GoatRunnerException("Incorrect Password");
		}
		
		// if driver doesn't exist in database
		throw new GoatRunnerException("You have to signup first");

		// con.close();
	}

	public static boolean driverValidation(int driverId) throws SQLException, GoatRunnerException {
		Connection con = connection.createConnection();
		PreparedStatement st;
		
			st = con.prepareStatement("select * from driver where driverId=?");
			st.setInt(1, driverId);
			ResultSet rs = st.executeQuery();
			if (rs != null) {

				if (rs.next() == true) {
					return true;
				}
				else {
					throw new GoatRunnerException("Driver does not exist");
				}
			}
		

		return false;
	}
	
}

package com.GoatRunner.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Path;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.User;

public class LoginService {
	private static ConnectionService connection = new ConnectionService();

	public static User login(int userid, String password) throws GoatRunnerException, SQLException {
		// ConnectionService connection = new ConnectionService(); // class for
		// connection
		Connection con = connection.createConnection();
		PreparedStatement st = con.prepareStatement("select * from passenger where studentid=?");
		st.setInt(1, userid);
		// st.setString(1, userid);

		ResultSet rs = st.executeQuery();
		String checkUser = "";
		String checkpwd = "";
		if (rs != null) {
			while (rs.next()) {
				checkUser = rs.getString(1);
				checkpwd = rs.getString(3);
			}
		}

		User user = new User();

		// if user exists and password is correct
		if (checkUser.equalsIgnoreCase(Integer.toString(userid)) && checkpwd.equalsIgnoreCase(password)) {
			PreparedStatement st1 = con.prepareStatement("SELECT * FROM PASSENGER WHERE STUDENTID = ?");
			st1.setInt(1, userid);
			ResultSet resultSet = st1.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					user.setAddress(resultSet.getString(1));
					user.setName(resultSet.getString(2));
				}
			}

			return user;

			// successful
		}
		// to check if password is incorrect
		if (checkUser.equals(userid) && !(checkpwd.equals(password))) {
			throw new GoatRunnerException("Incorrect Password");
		}
		// if user doesn't exist in database

		throw new GoatRunnerException("You have to signup first");

		// con.close();
	}

	public static void signup(User user) throws GoatRunnerException, SQLException {

		// ConnectionService connection = new ConnectionService(); // class for
		// connection
		Connection con = connection.createConnection();
		PreparedStatement st = con.prepareStatement("select * from passenger where studentId=?");
		st.setString(1, user.getStudent_id());

		ResultSet rs = st.executeQuery();
		String checkUser = "";
		if (rs != null) {

			if (rs.next() == true) {
				throw new GoatRunnerException("User Already Exist");
			} else {
				// adding user to database
				PreparedStatement st1 = con.prepareStatement(
						"INSERT INTO PASSENGER (studentId,studentname,pwd,phone_number,address,favlocation,email_address)"
								+ "VALUES (?,?,?,?,?,?,?)");
				st1.setString(1, user.getStudent_id());
				st1.setString(2, user.getName());
				st1.setString(3, user.getPassword());
				st1.setString(4, user.getPhone_number());
				st1.setString(5, user.getAddress());
				st1.setString(6, user.getFavourite_location());
				st1.setString(7, user.getEmail_id());
				st1.execute();
				st1.close();
				// creating a new user object

			}
		}

		// if user doesn't exist add the user to database

	}

	public static boolean userValidation(int userId) throws SQLException, GoatRunnerException {
		Connection con = connection.createConnection();
		PreparedStatement st;
		
			st = con.prepareStatement("select * from passenger where studentId=?");
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();
			if (rs != null) {

				if (rs.next() == true) {
					return true;
				}
				else {
					throw new GoatRunnerException("User does not exist");
				}
			}
		

		return false;
	}
}

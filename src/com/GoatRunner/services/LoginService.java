package com.GoatRunner.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Path;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.User;

public class LoginService {

	public static User login(String userid, String password) throws GoatRunnerException, SQLException {
		ConnectionService connection = new ConnectionService(); // class for
																// connection
		Connection con = connection.createConnection();
		PreparedStatement st = con.prepareStatement("select * from table where username=?");
		st.setString(1, userid);

		ResultSet rs = st.executeQuery();
		String checkUser = rs.getString(1);
		String checkpwd = rs.getString(2);
		User user = new User();
		// if user exists and password is correct
		if (checkUser.equals(userid) && checkpwd.equals(password)) {
			PreparedStatement st1 = con.prepareStatement("select * from Student where studentId=?");
			ResultSet resultSet = st1.executeQuery();
			user.setAddress(rs.getString(1));
			user.setName(rs.getString(2));
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

	public static void signup(String name, String student_id, String password,String email_id, String phone_number, String address,
			String favourite_location,String security_question, String answer) throws GoatRunnerException, SQLException {

		ConnectionService connection = new ConnectionService(); // class for
																// connection
		Connection con = connection.createConnection();
		PreparedStatement st = con.prepareStatement("select * from Table where username=?");
		st.setString(1, student_id);

		ResultSet rs = st.executeQuery();
		String checkUser = rs.getString(1);

		// check if user exists
		if (checkUser.equals(student_id)) {
			throw new GoatRunnerException("User Already Exist");
		}
		// if user doesn't exist add the user to database
		else {
			// adding user to database
			PreparedStatement st1 = con.prepareStatement("INSERT INTO Table "
					+ "VALUES (name, student_id, password, phone_number, address,favourite_location)");
			st1.executeQuery();
			st1.close();
			// creating a new user object
			User user = new User();
			user.setAddress(address);
			user.setEmail_id(email_id);
			user.setFavourite_location(favourite_location);
			user.setPassword(password);
			user.setName(name);
			user.setPhone_number(phone_number);
			user.setStudent_id(student_id);
			user.setSecurity_question(security_question);
			user.setAnswer(answer);
		}

	}

}

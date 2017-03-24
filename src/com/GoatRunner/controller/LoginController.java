package com.GoatRunner.controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.User;


public class LoginController {
	
	public void login(String userid, String password) throws GoatRunnerException, SQLException{
	    ConnectionController connection=new ConnectionController(); //class for connection
	    Connection con=connection.createConnection();
	    PreparedStatement st = con.prepareStatement("select * from Table where username=?");
	    st.setString(1, userid);

	    ResultSet rs=st.executeQuery(); 
	    String checkUser=rs.getString(1);
	    String checkpwd=rs.getString(2);
	    
	    //if user exists and password is correct
	    if(checkUser.equals(userid) && checkpwd.equals(password)){
	        //successful
	    }
	    //to check if password is incorrect
	    if(checkUser.equals(userid) && !(checkpwd.equals(password))){
	    	 throw new GoatRunnerException("Incorrect Password");
	    }
	    //if user doesn't exist in database
	    else{
	    	throw new GoatRunnerException("You have to signup first");
	    }
	  // con.close();	 
	}
	
	public void signup(String name,String student_id,String password,String phone_number,String address,String favourite_location) throws GoatRunnerException, SQLException{
		
	    ConnectionController connection=new ConnectionController(); //class for connection
	    Connection con=connection.createConnection();
	    PreparedStatement st = con.prepareStatement("select * from Table where username=?");
	    st.setString(1, student_id);

	    ResultSet rs=st.executeQuery(); 
	    String checkUser=rs.getString(1);
	    
		//check if user exists
		if(checkUser.equals(student_id)){
			 throw new GoatRunnerException("User Already Exist");
		    }
		//if user doesn't exist add the user to database
		else
		{	
		//adding user to database	
		PreparedStatement st1 = con.prepareStatement("INSERT INTO Table " + "VALUES (name, student_id, password, phone_number, address,favourite_location)");
		st1.executeQuery();
		st1.close();
		//creating a new user object
		User user = new User(name);
		user.setAddress(address);
		user.setFavourite_location(favourite_location);
		user.setPassword(password);
		user.setName(name);
		user.setPhone_number(phone_number);
		user.setStudent_id(student_id);
	}
	
	}		
		
	}


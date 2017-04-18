package com.GoatRunner.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.Booking;


public class BookingOtherService {
//to cancel a booking	
	public static void cancel(Booking book) throws GoatRunnerException, SQLException{
		ConnectionService connection = new ConnectionService();
		Connection con = connection.createConnection();
		PreparedStatement st = con.prepareStatement("UPDAPTE BOOKING SET cancel =1 where bookingId=?");
		st.setInt(1, book.getBooking_id());
		st.executeQuery();
		st.close();		
	}
//to complete a booking	
	public static void complete(Booking book) throws GoatRunnerException, SQLException{
		ConnectionService connection = new ConnectionService();
		Connection con = connection.createConnection();
		PreparedStatement st = con.prepareStatement("UPDAPTE BOOKING SET complete =1 where bookingId=?");
		st.setInt(1, book.getBooking_id());
		st.executeQuery();
		st.close();		
	}


}

package com.GoatRunner.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.BookingDetails;

public class BookingOtherService {
<<<<<<< HEAD
	// to cancel a booking
	public static void cancel(BookingDetails book) throws GoatRunnerException, SQLException {
		ConnectionService connection = new ConnectionService();
		Connection con = connection.createConnection();

		PreparedStatement st = con.prepareStatement("UPDAPTE BOOKING SET cancel =1 where bookingId=?");
		st.setInt(1, book.getBookingId());

=======
//to cancel a booking	
	public static void cancel(int bookingId) throws GoatRunnerException, SQLException {
		ConnectionService connection = new ConnectionService();
		Connection con = connection.createConnection();
		PreparedStatement st = con.prepareStatement("UPDATE BOOKING SET cancel =1 where bookingId=?");
		st.setInt(1, bookingId);
>>>>>>> cancel and confirm booking
		st.executeQuery();
		st.close();
	}
<<<<<<< HEAD

	// to complete a booking
	public static void complete(BookingDetails book) throws GoatRunnerException, SQLException {
=======
//to complete a booking	
		@Path("/logout")
		@GET
		public static void complete(int bookingId) throws GoatRunnerException, SQLException {
>>>>>>> cancel and confirm booking
		ConnectionService connection = new ConnectionService();
		Connection con = connection.createConnection();

		PreparedStatement st = con.prepareStatement("UPDATE BOOKING SET complete =1 where bookingId=?");
<<<<<<< HEAD
		st.setInt(1, book.getBookingId());

=======
		st.setInt(1, bookingId);
>>>>>>> cancel and confirm booking
		st.executeQuery();
		st.close();
	}

}

package com.GoatRunner.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.BookingDetails;

public class BookingOtherService {
	// to cancel a booking
	public static void cancel(BookingDetails book) throws GoatRunnerException, SQLException {
		ConnectionService connection = new ConnectionService();
		Connection con = connection.createConnection();

		PreparedStatement st = con.prepareStatement("UPDAPTE BOOKING SET cancel =1 where bookingId=?");
		st.setInt(1, book.getBookingId());

		st.executeQuery();
		st.close();
	}

	// to complete a booking
	public static void complete(BookingDetails book) throws GoatRunnerException, SQLException {
		ConnectionService connection = new ConnectionService();
		Connection con = connection.createConnection();

		PreparedStatement st = con.prepareStatement("UPDATE BOOKING SET complete =1 where bookingId=?");
		st.setInt(1, book.getBookingId());

		st.executeQuery();
		st.close();
	}

}

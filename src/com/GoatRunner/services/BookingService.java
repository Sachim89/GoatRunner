package com.GoatRunner.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.Booking;
import com.GoatRunner.model.User;

public class BookingService {
	public static Booking book(String userid,String to, String from,Float distance, Integer no_of_passengers) throws GoatRunnerException, SQLException {
		ConnectionService connection = new ConnectionService(); 
		Connection con = connection.createConnection();
		PreparedStatement st = con.prepareStatement("select cab_id from Cab where (8-current_capacity)>=?");
		st.setInt(1, no_of_passengers);
		ResultSet rs=st.executeQuery();
		String cab_id= rs.getString(1);
		
		PreparedStatement st2 = con.prepareStatement("select * from Driver where driver_id IN(select driver_id from Cab where cab_id=?) ");
		st.setString(1, cab_id);
		ResultSet rs1=st2.executeQuery();
		String driver_name= rs1.getString(1);
		String driver_phone=rs1.getString(3);
		
		//add current booking to the database
		PreparedStatement st1 = con.prepareStatement("INSERT INTO Booking"
							+ "VALUES (userid, cab_id, driver_id,driver_phone,distance, from, to, no_of_passengers, estimated_arrival)");
		st1.executeQuery();
		st1.close();
					
		PreparedStatement st3 = con.prepareStatement("select booking_id from Booking where student_id=? AND cab-id=?");
		st3.setString(1, userid);
		st3.setString(2, cab_id);
		ResultSet rs2=st3.executeQuery();
		int booking_id= rs2.getInt(1);
					
	    //creating a new booking object
		Booking booking = new Booking();
		booking.setCab_id(cab_id);
		booking.setDistance(distance);
		booking.setDriver_name(driver_name);
		booking.setDriver_phone(driver_phone);
		//booking.setEstimated_arrival(estimated_arrival);
		booking.setFrom(from);
		booking.setTo(to);
		booking.setNo_of_passengers(no_of_passengers);
		booking.setStudent_id(userid);
		booking.setBooking_id(booking_id);
		return booking;
					
	}
}

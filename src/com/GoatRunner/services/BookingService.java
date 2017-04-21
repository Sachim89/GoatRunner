package com.GoatRunner.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.BookingDetails;
import com.GoatRunner.util.LocationUtil;

public class BookingService {
	// final static int R = 6371;

	private static ConnectionService connection = new ConnectionService();

	// Check for capacity - Return a list
	// Get status - to get estimated time
	// CAlculate distance of cab's current location and user's location

	public static BookingDetails book(JSONObject bookingDetails) throws GoatRunnerException, JSONException {
		BookingDetails details = new BookingDetails();
		try {

			HashMap<Integer, String> optimalCab = getStatus(bookingDetails);
			Connection conn = connection.createConnection();
			PreparedStatement preparedStatement = conn.prepareStatement("select * from cab where cabno=?");
			int cabNO = (int) optimalCab.keySet().toArray()[0];
			preparedStatement.setInt(1, cabNO);

			int driverId = 0;

			int distance = LocationUtil.getDistance(bookingDetails.getDouble("sourceLatitude"),
					bookingDetails.getDouble("sourceLongitude"), bookingDetails.getDouble("destinationLatitude"),
					bookingDetails.getDouble("destinationLongitude"));
			details.setStdentId(bookingDetails.getInt("studentId"));
			details.setDistance(distance);
			details.setDestination(bookingDetails.getString("destination"));
			details.setSource(bookingDetails.getString("source"));
			details.setCabId(cabNO);
			details.setNoofPassenges(bookingDetails.getInt("noOfPassengers"));
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					driverId = resultSet.getInt(2);
					details.setDriverId(driverId);

				}
			}

			preparedStatement = conn.prepareStatement("select * from driver where driverid =?");
			preparedStatement.setInt(1, driverId);
			ResultSet resultSet2 = preparedStatement.executeQuery();
			if (resultSet2 != null) {
				while (resultSet2.next()) {
					details.setDriverName(resultSet2.getString(2));
				}
			}

			/*
			 * preparedStatement = conn.prepareStatement(
			 * "INSERT INTO BOOKINGDETAILS (cabno,driverid,studentid,distance,toplace,fromplace,noofpassengers)"
			 * + "VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			 */
			int bookingId = 0;
			String key[] = { "bookingid" };
			preparedStatement = conn.prepareStatement(
					"INSERT INTO BOOKINGDETAILS (cabno,driverid,studentid,distance,toplace,fromplace,noofpassengers)"
							+ "VALUES (?,?,?,?,?,?,?)",
					key);
			preparedStatement.setInt(1, details.getCabId());
			preparedStatement.setInt(2, details.getDriverId());
			preparedStatement.setInt(3, details.getStdentId());
			preparedStatement.setInt(4, details.getDistance());
			preparedStatement.setString(5, details.getDestination());
			preparedStatement.setString(6, details.getSource());
			preparedStatement.setInt(7, details.getNoofPassenges());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				details.setBookingId(rs.getInt(1));
			}
			//

			// ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			/*
			 * if (generatedKeys.next()) { // generatedKeys.getLong(1); }
			 */

			preparedStatement = conn.prepareStatement("UPDATE CAB SET NOOFPASSENGERS=?");
			preparedStatement.setInt(1, details.getNoofPassenges() + 1);
			preparedStatement.execute();
			preparedStatement.close();

		} catch (

		SQLException e) {
			throw new GoatRunnerException("SQL exception");
		}

		return details;
	}

	private static HashMap<Integer, String> getStatus(JSONObject bookingDetails) throws SQLException, JSONException {
		Connection conn = connection.createConnection();
		PreparedStatement st = conn.prepareStatement("select * from Cab where (current_capacity + ?) <=8");
		st.setInt(1, bookingDetails.getInt("noOfPassengers"));
		ResultSet rs = st.executeQuery();
		HashMap<Integer, Integer> cabDistList = new HashMap<>();
		HashMap<Integer, String> cabTimeList = new HashMap<>();
		HashMap<Integer, String> optimalCab = new HashMap<>();
		int cabNo = 0;
		if (rs != null) {
			while (rs.next()) {
				List<Integer> cabLocations = new ArrayList<>();
				cabNo = rs.getInt(1);
				double lat1 = rs.getDouble(5);
				double long1 = rs.getDouble(6);
				cabDistList.put(cabNo, LocationUtil.getDistance(lat1, long1, bookingDetails.getDouble("sourceLatitude"),
						bookingDetails.getDouble("sourceLongitude")));
				cabTimeList.put(cabNo, LocationUtil.getTime(lat1, long1, bookingDetails.getDouble("sourceLatitude"),
						bookingDetails.getDouble("sourceLongitude")));
				rs.getString(1);
			}
		}

		Iterator it = cabDistList.entrySet().iterator();
		Collection<Integer> list = cabDistList.values();
		double minDistance = list.iterator().next();
		int optimalCabNo = 0;
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			int D = (int) pair.getValue();
			if (minDistance >= D) {
				minDistance = D;
			}
			// minDistance = D;
			optimalCabNo = (int) pair.getKey();
			System.out.println(pair.getKey() + " = " + D);
		}

		optimalCab.put(optimalCabNo, cabTimeList.get(optimalCabNo));
		cabTimeList.get(12);

		return optimalCab;
		// return null;
	}

}

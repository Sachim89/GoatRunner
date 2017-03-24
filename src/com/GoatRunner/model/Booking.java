package com.GoatRunner.model;

public class Booking {
	private int booking_id;
	private String cab_id;
	private int driver_id;
	private String estimated_arrival;
	private int student_id;
	private float distance;
	private String to;
	private String from;
	private int no_of_passengers;
	
	
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	public String getCab_id() {
		return cab_id;
	}
	public void setCab_id(String cab_id) {
		this.cab_id = cab_id;
	}
	public int getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}
	public String getEstimated_arrival() {
		return estimated_arrival;
	}
	public void setEstimated_arrival(String estimated_arrival) {
		this.estimated_arrival = estimated_arrival;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public int getNo_of_passengers() {
		return no_of_passengers;
	}
	public void setNo_of_passengers(int no_of_passengers) {
		this.no_of_passengers = no_of_passengers;
	}	
	

}

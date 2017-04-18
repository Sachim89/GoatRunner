package com.GoatRunner.model;

public class BookingDetails {
	
	private int bookingId;
	
	private int cabId;
	
	private int driverId;
	
	private int studentId;
	
	private int distance;
	
	private String destination;
	
	private String source;
	
	private int notOfPassengers;
	
	private String estimatedTime;

	
	
	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getCabId() {
		return cabId;
	}

	public void setCabId(int cabId) {
		this.cabId = cabId;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public int getStdentId() {
		return studentId;
	}

	public void setStdentId(int stdentId) {
		this.studentId = stdentId;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getNoofPassenges() {
		return notOfPassengers;
	}

	public void setNoofPassenges(int noofPassenges) {
		this.notOfPassengers = noofPassenges;
	}

	public String getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

}

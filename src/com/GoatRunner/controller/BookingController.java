package com.GoatRunner.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.GoatRunner.model.Booking;

@Path("/booking")
public class BookingController {
	@Path("/book")
	@GET
	public Response bookingARide(@PathParam("studentId") String studentId, @PathParam("from") String from,
			@PathParam("to") String to,@PathParam("distance") Float distance,
			@PathParam("no_of_passengers") int no_of_passengers) {

		Booking booking = new Booking();
		//try {
			//booking = BookingService.book(studentId, from,to,distance,no_of_passengers);
		/*} catch (GoatRunnerException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (SQLException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}*/
		return Response.status(Status.OK).entity(booking).build();
	}

	
}

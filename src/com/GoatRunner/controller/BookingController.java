package com.GoatRunner.controller;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.BookingDetails;
import com.GoatRunner.services.BookingOtherService;
import com.GoatRunner.services.BookingService;

/**
 * This class handles booking requests for booking and cancellation 
 * @author Apoorva
 *
 */
@Path("/ride")
public class BookingController {

	/**
	 * 
	 * @param booking
	 * @return
	 */
	@Path("/book")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response bookARide(BookingDetails bookingDetails) {
		System.out.println("Entered book");
		try {
			BookingService.book(bookingDetails);
		} catch (GoatRunnerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.OK).entity(null).build();
	}

	@Path("/cancel")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response cancellingARide(BookingDetails book ) {
		
		System.out.println("Entered");
		try {
			BookingOtherService.cancel(book);
		} catch (GoatRunnerException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.OK).build();
	}
	
	@Path("/complete")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response completingARide(BookingDetails book ) {
		
		System.out.println("Entered");
		try {
			BookingOtherService.complete(book);
		} catch (GoatRunnerException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.OK).build();
	}

		
	

}

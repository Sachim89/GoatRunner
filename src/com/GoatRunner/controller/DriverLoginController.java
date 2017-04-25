package com.GoatRunner.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.Cab;
import com.GoatRunner.model.Driver;
import com.GoatRunner.services.DriverLoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Path("/driver")
public class DriverLoginController {
	
		ObjectMapper mapper = new ObjectMapper();
		@Context
		private HttpServletRequest httpServletRequest;

		@Path("/login_driver")
		@GET
		public Response loginDriver(@QueryParam("driver_Id") int driver_Id, @QueryParam("password") String password) {
			System.out.println("Entered");
			Driver driver= new Driver();
			String responseObject = "";
			try {
				driver = DriverLoginService.login_driver(driver_Id, password);
				HttpSession session = httpServletRequest.getSession(true);
				session.setAttribute(Integer.toString(driver_Id), driver);
				session.setMaxInactiveInterval(420);
			} catch (GoatRunnerException e) {
				return Response.status(Status.BAD_REQUEST).build();
			} catch (SQLException e) {
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}
			try {
				responseObject = mapper.writeValueAsString(driver);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return Response.status(Status.OK).entity(responseObject).build();
		}
	
		@Path("/get_cab")
		@GET
		public Response cabToDriver(@QueryParam("driver_Id") int driver_Id) {
			System.out.println("Entered");
			Cab cab = new Cab();
			String responseObject = "";
			try {
				cab = DriverLoginService.cabToDriver(driver_Id);
				HttpSession session = httpServletRequest.getSession(true);
				session.setAttribute(Integer.toString(driver_Id), cab);
				session.setMaxInactiveInterval(420);
			} catch (GoatRunnerException e) {
				return Response.status(Status.BAD_REQUEST).build();
			} catch (SQLException e) {
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}
			try {
				responseObject = mapper.writeValueAsString(cab);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return Response.status(Status.OK).entity(responseObject).build();
		}
		
		
	@Path("/logout")
	@GET
	public Response logout(@QueryParam("driverId") int driverId) {
		String response = "";
		try {
			if (DriverLoginService.driverValidation(driverId)) {

				response = "Driver successfully logged out";
				HttpSession session = httpServletRequest.getSession(false);
				session.removeAttribute(Integer.toString(driverId));
				session.getMaxInactiveInterval();
			}
		} catch (SQLException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Driver could not logout").build();
		} catch (GoatRunnerException e) {
			return Response.status(Status.BAD_REQUEST).entity("The Driver does not exist").build();
		}
		return Response.status(Status.OK).entity(response).build();
	}
}

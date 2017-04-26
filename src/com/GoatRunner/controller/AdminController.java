package com.GoatRunner.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONException;
import org.json.JSONObject;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.BookingDetails;
import com.GoatRunner.model.CurrentRides;
import com.GoatRunner.services.AdminService;
import com.GoatRunner.services.BookingOtherService;
import com.GoatRunner.services.BookingService;
import com.GoatRunner.services.DriverService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AdminController {
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Context
	private HttpServletRequest httpServletRequest;

	/**
	 * 
	 * @param admin
	 * @return
	 */
	@Path("/admin")
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })

	public Response adminView(@QueryParam("cabNo") Integer cabNo) {
		System.out.println("Entered cab details");
		String responseObject = "";
		List<CurrentRides> list = new ArrayList<CurrentRides>();
		try {
			list = AdminService.cancel(cabNo);
			HttpSession session = httpServletRequest.getSession(true);
			session.setAttribute(Integer.toString(cabNo), list);
			session.setMaxInactiveInterval(420);
		} catch (GoatRunnerException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (SQLException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		try {
			responseObject = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(Status.OK).entity(responseObject).build();
	}
	
}

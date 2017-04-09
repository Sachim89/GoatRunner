package com.GoatRunner.controller;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.User;
import com.GoatRunner.services.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/user")
public class LoginController {

	ObjectMapper mapper = new ObjectMapper();

	@Path("/login")
	@GET
	public Response loginUser(@QueryParam("userId") int studentId, @QueryParam("password") String password) {
		System.out.println("ENtered");
		User user = new User();
		String responseObject = "";
		try {
			user = LoginService.login(studentId, password);
		} catch (GoatRunnerException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (SQLException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		try {
			responseObject = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(Status.OK).entity(responseObject).build();
	}

	@Path("/signup")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response signUp(User user) {

		System.out.println("Entered");
		try {
			LoginService.signup(user);
		} catch (GoatRunnerException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.OK).build();
	}

}

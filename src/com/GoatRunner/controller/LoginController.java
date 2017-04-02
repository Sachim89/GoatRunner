package com.GoatRunner.controller;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.User;
import com.GoatRunner.services.LoginService;

@Path("/user")
public class LoginController {

	@Path("/login")
	@GET
	public Response loginUser(@QueryParam("userId") String studentId, @QueryParam("password") String password) {
		System.out.println("ENtered");
		User user = new User();
		try {
			user = LoginService.login(studentId, password);
		} catch (GoatRunnerException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (SQLException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.OK).entity(user).build();
	}

	@Path("/signup")
	@POST
	public Response signUp( User user) {
		
			System.out.println("Entered");
			//LoginService.signup(name, student_id, password,email_id, phone_number, address, favourite_location,security_question,answer);
		
		return Response.status(Status.OK).build();
	}

}

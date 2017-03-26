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
	public Response loginUser(@PathParam("userId") String studentId, @PathParam("password") String password) {

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
	public Response signUp(@QueryParam("name") String name, @QueryParam("student_id") String student_id,
			@QueryParam("password") String password, @QueryParam("phone_number") String phone_number,
			@QueryParam("address") String address, @QueryParam("favourite_location") String favourite_location) {
		try {
			LoginService.signup(name, student_id, password, phone_number, address, favourite_location);
		} catch (GoatRunnerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.OK).build();
	}

}

package ir.maktab.finalproject.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import ir.maktab.finalproject.entities.Users;
import ir.maktab.finalproject.service.DescribesServiceImpl;
import ir.maktab.finalproject.service.UserServiceImpl;

@Path("/users")
public class UserController {
	UserServiceImpl ul = UserServiceImpl.getUserServiceImpl();
	DescribesServiceImpl dl = DescribesServiceImpl.getDescribesServiceImpl();
	private static Logger logger = org.apache.log4j.LogManager.getLogger(UserController.class);
	HttpSession session;

	// post user for save in database
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(Users users, @Context HttpServletRequest request, @Context HttpServletResponse response)
			throws Exception {
		logger.info("Registering User in System");
		session = request.getSession();
		dl.add(new Date() + " Registering User in System", "guest");
		ul.add(users);
		return Response.status(200).entity("you can add user in system").build();

	}

	// send request for get all users from database
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@Context HttpServletRequest request, @Context HttpServletResponse response)
			throws Exception {
		logger.info("Getting All Users");
		session = request.getSession();
		String i = session.getAttribute("roleId").toString();
		if (i.equals("3")) {
			dl.add(new Date() + "     Getting All Users", session.getAttribute("user").toString());
			return Response.status(200).entity(ul.getAll()).build();
		} else {
			return Response.status(401).entity("you can't access to this web service").build();
		}
	}

	// send request for login in system
	@GET
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getUserDetails(@Context HttpServletRequest request, @Context HttpServletResponse response)
			throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		session = request.getSession();
		session.setAttribute("user", username);
		session.setAttribute("roleId", ul.loginUsers(username, password));
		dl.add(new Date() + "     Login User To System", session.getAttribute("user").toString());
		logger.info("Login User To System");
		if (ul.loginUsers(username, password) == 0)
			return Response.status(401).entity("your information incorrect for login in system").build();
		else {
			return Response.status(200).entity(ul.loginUsers(username, password)).build();
		}
	}

	// send request for logout from system
	@GET
	@Path("/logout")
	@Produces(MediaType.TEXT_PLAIN)
	public void getUserLogOut(@Context HttpServletRequest request) throws Exception {
		session = request.getSession();
		dl.add(new Date() + "     Logout user From System", session.getAttribute("user").toString());
		logger.info("Logout User From System");
		session.setAttribute("user", "guest");
	}

	// send user's information for update in database
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editUser(Users users, @Context HttpServletRequest request, @Context HttpServletResponse response)
			throws Exception {
		logger.info("Edit User");
		session = request.getSession();
		String i = session.getAttribute("roleId").toString();
		if (i.equals("3")) {
			dl.add(new Date() + "     Edit User ", session.getAttribute("user").toString());
			ul.edit(users);
			return Response.status(200).entity("you can update user in system").build();
		} else {
			return Response.status(401).entity("you can't access to this web service").build();
		}
	}

	// send user's information for delete from database
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeUser(Users users, @Context HttpServletRequest request, @Context HttpServletResponse response)
			throws Exception {
		logger.info("Remove User From System");
		session = request.getSession();
		String i = session.getAttribute("roleId").toString();
		if (i.equals("3")) {
			dl.add(new Date() + "     Remove User From System", session.getAttribute("user").toString());
			ul.remove(users);
			return Response.status(200).entity("you can delete user from system").build();
		} else {
			return Response.status(401).entity("you can't access to this web service").build();
		}

	}

}

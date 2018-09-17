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

import ir.maktab.finalproject.entities.Calls;
import ir.maktab.finalproject.service.CallsServiceImpl;
import ir.maktab.finalproject.service.DescribesServiceImpl;

@Path("/calls")
public class CallsController {
	private static Logger logger = org.apache.log4j.LogManager.getLogger(CallsController.class);
	CallsServiceImpl callsService = CallsServiceImpl.getCallsServiceImpl();
	DescribesServiceImpl dl = DescribesServiceImpl.getDescribesServiceImpl();

	// post a contact for save in database
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCall(Calls call, @Context HttpServletRequest request, @Context HttpServletResponse response)
			throws Exception {
		logger.info("Insert contact");
		 HttpSession session = request.getSession();
		 String role = session.getAttribute("roleId").toString();
		 if (role.equals("4"))
		 return Response.status(401).entity("you Can't access to this web service").build();
		 else {
		 dl.add(new Date() + " Add Call In System ",
		 session.getAttribute("user").toString());
		callsService.add(call);
		return Response.status(200).entity("you can add contact in system").build();
		 }
	}

	// send request for get all contacts
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		logger.info("Geting all contacts");
		return Response.status(200).entity(callsService.getAll()).build();
	}

	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchContact(String[][] str) throws Exception {
		logger.info("search for contact");
		return Response.status(200).entity(callsService.toFind(str)).build();
	}

	// send a contact for update
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editCall(Calls calls, @Context HttpServletRequest request, @Context HttpServletResponse response)
			throws Exception {
		logger.info("Edit contacts");
		HttpSession session = request.getSession();
		String role = session.getAttribute("roleId").toString();
		if (role.equals("1") || role.equals("4"))
			return Response.status(401).entity("you Can't access to this web service").build();
		else {
			dl.add(new Date() + "     Edit Call In System ", session.getAttribute("user").toString());
			callsService.edit(calls);
			return Response.status(200).entity("you can update contact in system").build();
		}
	}

	// send a contact's information for delete from database
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeCall(Calls calls, @Context HttpServletRequest request, @Context HttpServletResponse response)
			throws Exception {
		logger.info("Delete contacts");
		HttpSession session = request.getSession();
		String role = session.getAttribute("roleId").toString();
		if (role.equals("1") || role.equals("4"))
			return Response.status(401).entity("you Can't access to this web service").build();
		else {
			dl.add(new Date() + "     Delete Call From System ", session.getAttribute("user").toString());
			callsService.remove(calls);
			return Response.status(200).entity("you can delete contact from system").build();
		}
	}

}

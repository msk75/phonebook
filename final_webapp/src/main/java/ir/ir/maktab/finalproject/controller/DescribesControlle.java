package ir.maktab.finalproject.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ir.maktab.finalproject.service.DescribesServiceImpl;

@Path("/describes")
public class DescribesControlle {
	DescribesServiceImpl daoImpl = DescribesServiceImpl.getDescribesServiceImpl();
	DescribesServiceImpl dl = DescribesServiceImpl.getDescribesServiceImpl();

	// send request for gett all describes
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@Context HttpServletRequest request, @Context HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String role = session.getAttribute("roleId").toString();
		if (role.equals("1") || role.equals("4"))
			return Response.status(401).entity("you can't access to this web service").build();
		else {
			dl.add(new Date() + "     Getting User's Activity ", session.getAttribute("user").toString());
			return Response.status(200).entity(daoImpl.getAll()).build();
		}
	}
}

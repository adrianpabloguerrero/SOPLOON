package ar.edu.unicen.isistan.si.soploon.server.services.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import ar.edu.unicen.isistan.si.soploon.server.database.Database;
import ar.edu.unicen.isistan.si.soploon.server.database.ErrorDao;
import ar.edu.unicen.isistan.si.soploon.server.models.Error;



public class ErrorResource {
	
	private Database database;
	private ErrorDao dao;

	public ErrorResource (Database database) {
		this.database = database;
		this.dao = new ErrorDao(this.database);
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addErrors(@PathParam("user_id") int userId, @PathParam("project_id") int projectId,@PathParam("time") int time, List <Error> errors, @Context UriInfo uriInfo) throws Exception {
		//TODO chequeos 
		if (errors == null)
			return Response.status(Response.Status.BAD_REQUEST).build();
			if (this.dao.insert(errors)) {
				UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
				return Response.created(uriBuilder.build()).entity(errors).build();
			} else {
				return Response.status(Status.CONFLICT).build();
			}
	}
}




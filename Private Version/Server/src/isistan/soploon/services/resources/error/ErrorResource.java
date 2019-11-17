package isistan.soploon.services.resources.error;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import isistan.soploon.database.Database;



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
	public Response addProject(Error error, @Context UriInfo uriInfo) throws Exception {
		if (error == null)
			return Response.status(Response.Status.BAD_REQUEST).build();
			if (this.dao.insert(error)) {
				UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
				uriBuilder.path(Integer.toString(error.getId()));
				return Response.created(uriBuilder.build()).entity(error).build();
			} else {
				return Response.status(Status.CONFLICT).build();
			}
	}
}




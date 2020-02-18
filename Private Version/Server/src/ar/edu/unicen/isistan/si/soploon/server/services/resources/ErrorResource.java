package ar.edu.unicen.isistan.si.soploon.server.services.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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

	public ErrorResource(Database database) {
		this.database = database;
		this.dao = new ErrorDao(this.database);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getErrors(@PathParam("user_id") int userId, @PathParam("project_id") int projectId, @PathParam("time") long time) throws Exception {
		List<Error> errors = dao.getErrorsByCorrection(userId, projectId, time);
		if (errors == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(errors).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{error_id}")
	public Response getErrors(@PathParam("user_id") int userId, @PathParam("project_id") int projectId, @PathParam("time") long time, @PathParam("error_id") int errorId) throws Exception {
		Error error = dao.getErrorsById(errorId);
		if (error == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(error).build();
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addErrors(@PathParam("user_id") int userId, @PathParam("project_id") int projectId, @PathParam("time") long time, List<Error> errors, @Context UriInfo uriInfo) throws Exception {
		if (!validateAddErrors(errors,userId,projectId,time))
			return Response.status(Response.Status.BAD_REQUEST).build();

		if (errors == null)
			return Response.status(Response.Status.BAD_REQUEST).build();
		if (this.dao.insert(errors)) {
			UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
			return Response.created(uriBuilder.build()).entity(errors).build();
		} else
			return Response.status(Status.CONFLICT).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{error_id}")
	public Response updateError(@PathParam("user_id") int userId, @PathParam("project_id") int projectId, @PathParam("time") long time, @PathParam("error_id") int errorId, Error updatedError, @Context UriInfo uriInfo) throws Exception {
		Error error = dao.getErrorsById(errorId);

		if (!validateUpdatedErrors(updatedError,error,errorId,userId,projectId,time)) 
			return Response.status(Status.BAD_REQUEST).build();

		if (this.dao.updateError(updatedError))
			return Response.ok(updatedError).build();
		else
			return Response.status(Status.BAD_REQUEST).build();
	}

	private boolean validateAddErrors (List<Error> errors, int userId, int projectId, long time) {
		for (Error error: errors)
			if (error.getUserId() != userId || error.getProjectId() != projectId || error.getDate() != time)
				return false;
		return true;
	}

	private boolean validateUpdatedErrors (Error error, Error updatedError, int errorId, int userId, int projectId, long time) {
		if (error.getId() != errorId || error.getUserId() != userId || error.getProjectId() != projectId || error.getDate() != time || !error.equals(updatedError))
			return false;
		return true;
	}
}

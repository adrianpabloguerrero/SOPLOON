package isistan.soploon.services.resources;

import java.sql.SQLException;
import java.util.ArrayList;

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

import isistan.soploon.database.Database;
import isistan.soploon.models.project.Project;
import isistan.soploon.models.project.ProjectDao;

public class ProjectResource {
	
	private Database database;
	private ProjectDao dao;
	
	public ProjectResource (Database database) {
		this.database = database;
		this.dao = new ProjectDao(this.database);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProject(Project project, @Context UriInfo uriInfo) throws SQLException {
		if (project == null)
			return Response.status(Response.Status.BAD_REQUEST).build();
	try {
		if (this.dao.insert(project)) {
			UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
			uriBuilder.path(Integer.toString(project.getId()));
			return Response.created(uriBuilder.build()).entity(project).build();
		} else {
			return Response.status(Status.CONFLICT).build();
		}
	} catch (Exception e) {
		e.printStackTrace();
		return Response.serverError().build();
	}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}/")
	public Response getProjectByIdUser(@PathParam("id") int id) {
		try {
			ArrayList <Project> project = this.dao.getProjectByIdUser(id);
			if (project == null)
				return Response.status(Status.NOT_FOUND).build();
			return Response.ok(project).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	
	
	
	/*
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path ("/{id}/")
	public Response editRule (@PathParam("id") int id, Project project ) {
		if (dao.updateProject(id, project))
			return Response.ok(project).build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
*/
}

package isistan.soploon.project;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import isistan.soploon.database.Database;
import isistan.soploon.rule.Rule;

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
	public Response addProject(Project project) {
			if (dao.insert(project))
				return Response.status(Response.Status.CREATED).build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path ("/{id}/")
	public Response getProjectByIdUser (@PathParam("id") int id) {
		return Response.ok(dao.getProjectByIdUser(id)).build();	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path ("/{id}/")
	public Response editRule (@PathParam("id") int id, Project project ) {
		if (dao.updateProject(id, project))
			return Response.ok(project).build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

}

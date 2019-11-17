package isistan.soploon.services.resources.projects;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import isistan.soploon.services.resources.correction.CorrectionResource;

public class ProjectResource {

	private Database database;
	private ProjectDao dao;
	private CorrectionResource correctionResuorce;
	
	public ProjectResource(Database database) {
		this.database = database;
		this.dao = new ProjectDao(this.database);
		this.correctionResuorce = new CorrectionResource(this.database);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProject(@PathParam("user_id") int userId, Project project, @Context UriInfo uriInfo) throws Exception {
		if (project == null || userId == 0 || project.getUserId() != userId || project.getId() != 0 || !project.check())
			return Response.status(Status.BAD_REQUEST).build();
		
		if (this.dao.insert(project)) {
			UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
			uriBuilder.path(Integer.toString(project.getId()));
			return Response.created(uriBuilder.build()).entity(project).build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjects(@PathParam("user_id") int userId) throws Exception {
		ArrayList<Project> projects = this.dao.getProjectsByUser(userId);
		return Response.ok(projects).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{project_id}/")
	public Response getProject(@PathParam("user_id") int userId, @PathParam("project_id") int projectId) throws Exception {
		Project project = this.dao.getProject(userId, projectId);
		if (project == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok(project).build();
	}

	
	@Path("/{project_id}/corrections")
	public CorrectionResource corrections(@PathParam("user_id") int userId, @PathParam("project_id") int projectId) throws Exception {
		Project project = this.dao.getProject(userId, projectId);
		if (project == null)
			return null;
		return this.correctionResuorce;
	}
}

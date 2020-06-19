package ar.edu.unicen.isistan.si.soploon.server.services.resources;

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

import ar.edu.unicen.isistan.si.soploon.server.database.Database;
import ar.edu.unicen.isistan.si.soploon.server.database.UserDao;
import ar.edu.unicen.isistan.si.soploon.server.models.User;
import ar.edu.unicen.isistan.si.soploon.server.providers.Secured;

public class UserResource {

	private Database database;
	private UserDao dao;
	private ProjectResource projectResource;

	public UserResource(Database database) {
		this.database = database;
		this.dao = new UserDao(this.database);
		this.projectResource = new ProjectResource(this.database);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user, @Context UriInfo uriInfo) throws Exception {
		if (user == null || user.getId() != 0 || !user.check())
			return Response.status(Status.BAD_REQUEST).build();

		if (this.dao.insert(user)) {
			UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
			uriBuilder.path(Integer.toString(user.getId()));
			return Response.created(uriBuilder.build()).entity(user).build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{user_id}/")
	@Secured
	public Response getUser(@PathParam("user_id") int userId) throws Exception {
		User user = this.dao.getUser(userId);
		if (user == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok(user).build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response getUsers() throws Exception {
		ArrayList<User> users = this.dao.getUsers();
		return Response.ok(users).build();

	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{user_id}/")
	@Secured
	public Response editUser(@PathParam("user_id") int userId, User updatedUser) throws Exception {
		if (updatedUser == null || updatedUser.getId() != userId || !updatedUser.check())
			return Response.status(Status.BAD_REQUEST).build();

		User user = this.dao.getUser(userId);
		if (user == null)
			return Response.status(Status.NOT_FOUND).build();

		if (this.dao.updateUser(updatedUser))
			return Response.ok(updatedUser).build();
		else
			return Response.status(Status.BAD_REQUEST).build();
	}

	@Path("/{user_id}/projects")
	@Secured
	public ProjectResource projects(@PathParam("user_id") int userId) throws Exception {
		User user = this.dao.getUser(userId);
		if (user == null)
			return null;
		return this.projectResource;
	}

}

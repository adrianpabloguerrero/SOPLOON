package isistan.soploon.services.resources;

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
import isistan.soploon.models.rule.Rule;
import isistan.soploon.models.user.User;
import isistan.soploon.models.user.UserDao;

public class UserResource {
	
	private Database database;
	private UserDao dao;


	public UserResource(Database database) {
		this.database = database;
		this.dao = new UserDao(this.database);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user, @Context UriInfo uriInfo) {
		System.out.println("add user");
			if (user == null)
				return Response.status(Response.Status.BAD_REQUEST).build();
		try {
			if (this.dao.insert(user)) {
				UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
				uriBuilder.path(Integer.toString(user.getId()));
				return Response.created(uriBuilder.build()).entity(user).build();
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
	public Response getUser(@PathParam("id") int id) {
		try {
			User user = this.dao.getUser(id);
			if (user == null)
				return Response.status(Status.NOT_FOUND).build();
			return Response.ok(user).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

}

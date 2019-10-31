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
import isistan.soploon.models.predicate.Predicate;
import isistan.soploon.models.predicate.PredicateDao;

public class PredicateResource {

	private Database database;
	private PredicateDao dao;

	public PredicateResource (Database database) {
		this.database = database;
		this.dao = new PredicateDao(this.database);
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProject(Predicate predicate, @Context UriInfo uriInfo) throws SQLException {
		if (predicate == null)
			return Response.status(Response.Status.BAD_REQUEST).build();
		try {
			if (this.dao.insert(predicate)) {
				UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
				uriBuilder.path(Integer.toString(predicate.getId()));
				return Response.created(uriBuilder.build()).entity(predicate).build();
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
	public Response getPredicate (@PathParam("id") int id) {
		try {
			ArrayList <Predicate> predicate = this.dao.getPredicate(id);
			if (predicate == null)
				return Response.status(Status.NOT_FOUND).build();
			return Response.ok(predicate).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPredicate() {
		try {
			ArrayList <Predicate> predicates = this.dao.getPredicates();
			if (predicates == null)
				return Response.status(Status.NOT_FOUND).build();
			return Response.ok(predicates).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}/")
	public Response editPredicate(@PathParam("id") int id, Predicate predicate) throws SQLException {
		if (this.dao.updatePredicate(id, predicate))
		 return Response.ok(predicate).build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	
	


}

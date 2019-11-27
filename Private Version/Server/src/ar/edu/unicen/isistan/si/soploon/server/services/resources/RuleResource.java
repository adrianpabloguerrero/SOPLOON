package ar.edu.unicen.isistan.si.soploon.server.services.resources;

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
import javax.ws.rs.core.Response.Status;

import ar.edu.unicen.isistan.si.soploon.server.database.Database;
import ar.edu.unicen.isistan.si.soploon.server.database.RuleDao;
import ar.edu.unicen.isistan.si.soploon.server.models.Rule;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

public class RuleResource {

	private Database database;
	private RuleDao dao;

	public RuleResource(Database database) {
		this.database = database;
		this.dao = new RuleDao(this.database);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRules() {
		try {
			ArrayList<Rule> rules = this.dao.getRules();
			return Response.ok(rules).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}/")
	public Response getRule(@PathParam("id") int id) {
		try {
			Rule rule = this.dao.getRule(id);
			if (rule == null)
				return Response.status(Status.NOT_FOUND).build();
			return Response.ok(rule).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}/versions/")
	public Response getRuleVersions(@PathParam("id") int id) {
		try {
			ArrayList<Rule> rules = this.dao.getRuleVersions(id);
			return Response.ok(rules).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRule(Rule rule, @Context UriInfo uriInfo) {
		//Este if valida si la la regla es valida. Lo comento para pruebas.
		//if (rule == null || rule.getId() != 0 || !rule.isValid())
			if (rule == null || rule.getId() != 0)
				return Response.status(Response.Status.BAD_REQUEST).build();
		try {
			if (this.dao.insert(rule)) {
				UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
				uriBuilder.path(Integer.toString(rule.getId()));
				return Response.created(uriBuilder.build()).entity(rule).build();
			} else {
				return Response.status(Status.CONFLICT).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}/")
	public Response editRule(@PathParam("id") int id, Rule rule) throws SQLException {
		if (this.dao.updateRule(id,rule))
		 return Response.ok(rule).build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

}

package isistan.soploon.services.resources;

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
import isistan.soploon.models.rule.Rule;
import isistan.soploon.models.rule.RuleDao;

public class RuleResource {

	private Database database;
	private RuleDao dao;

	public RuleResource(Database database) {
		this.database = database;
		this.dao = new RuleDao(this.database);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path ("/{id}/")
	public Response getRule (@PathParam("id") int id) {
		return Response.ok(dao.getRuleById(id)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRule (Rule rule) {
			if (dao.insert(rule))
				return Response.status(Response.Status.CREATED).build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path ("/{id}/")
	public Response editRule (@PathParam("id") int id, Rule rule ) {
		if (dao.updateRule(id, rule))
			return Response.ok(rule).build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

}

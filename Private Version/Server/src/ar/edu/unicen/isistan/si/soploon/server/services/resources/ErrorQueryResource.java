package ar.edu.unicen.isistan.si.soploon.server.services.resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import ar.edu.unicen.isistan.si.soploon.server.database.Database;
import ar.edu.unicen.isistan.si.soploon.server.database.ErrorDao;
import ar.edu.unicen.isistan.si.soploon.server.models.Error;


public class ErrorQueryResource {

	private Database database;
	private ErrorDao dao;

	public ErrorQueryResource (Database database) {
		this.database = database;
		this.dao = new ErrorDao (this.database);
	}



	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getErrors (@QueryParam("error_id") Integer id, @QueryParam("user_id") Integer userId, @QueryParam("date_start") Long dateStart, @QueryParam("date_end") Long dateEnd, @QueryParam("rule_id") Integer ruleId) throws SQLException {
		List <Error> errors = new  ArrayList <Error> ();
		if (id != null)
			errors.add(this.dao.getErrorsById(id));
		else {
			if (dateStart ==  null) dateStart =  0L;
			if (dateEnd == null) dateEnd = new Date().getTime();
			
			if (userId != null)
				errors.addAll(this.dao.getErrorsByUserBetweenDate(userId,dateStart,dateEnd));
			
			if (ruleId != null)
				errors.addAll(this.dao.getErrorsByRuleBetweenDate(ruleId,dateStart,dateEnd));			
			else {
				errors.addAll(this.dao.getErrorsBetweenDate(dateStart,dateEnd));
			}		
		}

		if (!errors.isEmpty())
			return Response.ok(errors).build();
		else
			return Response.status(Response.Status.NOT_FOUND).build();
	}

}

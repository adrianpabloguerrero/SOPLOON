package ar.edu.unicen.isistan.si.soploon.server.services.resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
	public Response getErrors (@QueryParam("error_id") Integer id) throws SQLException {
		List <Error> errors = new  ArrayList <Error> ();
		if (id != null)
			errors.add(this.dao.getErrorsById(id));
		if (!errors.isEmpty())
			return Response.ok(errors).build();
		else
			return Response.status(Response.Status.NOT_FOUND).build();
	}
	
}

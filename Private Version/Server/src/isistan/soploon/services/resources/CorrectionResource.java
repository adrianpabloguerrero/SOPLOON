package isistan.soploon.services.resources;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import isistan.soploon.database.Database;
import isistan.soploon.models.correction.Correction;
import isistan.soploon.models.correction.CorrectionDao;

public class CorrectionResource {

	private Database database;
	private CorrectionDao dao;
	
	
	public CorrectionResource (Database database) {
		this.database = database;
		this.dao = new CorrectionDao(this.database);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProject(Correction correction, @Context UriInfo uriInfo) throws SQLException {
		if (correction == null)
			return Response.status(Response.Status.BAD_REQUEST).build();
		try {
			if (this.dao.insert(correction)) {
				UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
				uriBuilder.path(Integer.toString(correction.getUserId())+Integer.toString(correction.getProjectId())+Long.toString(correction.getDate()));
				return Response.created(uriBuilder.build()).entity(correction).build();
			} else {
				return Response.status(Status.CONFLICT).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	
	
	
}

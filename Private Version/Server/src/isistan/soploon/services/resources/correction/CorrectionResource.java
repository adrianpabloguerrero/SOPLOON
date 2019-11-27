package isistan.soploon.services.resources.correction;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import isistan.soploon.database.Database;


// TODO, HACER LOS METODOS HTTP DE ESTA CLASE, SOLO INSERT Y GET, NO HAY UPDATE
// NOTA: EL INSERT DEBERIA TRAER CONSIGO TODA LA LISTA DE ERRORES, NO TIENE SENTIDO INSERTAR DE A UN ERROR

public class CorrectionResource {

	private Database database;
	private CorrectionDao dao;
	
	public CorrectionResource (Database database) {
		this.database = database;
		this.dao = new CorrectionDao(this.database);
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCorrections(@PathParam("user_id") int userId, @PathParam("project_id") int projectId) throws Exception {
		ArrayList <Correction> corrections = this.dao.getProjects();
		return Response.ok(corrections).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCorrection(@PathParam("user_id") int userId, @PathParam("project_id") int projectId,Correction correction, @Context UriInfo uriInfo) throws Exception {

		if (correction == null || userId == 0 || projectId == 0|| correction.getUserId() != userId || correction.getProjectId() != projectId )
			return Response.status(Status.BAD_REQUEST).build();

		if (this.dao.insert(correction)) {
				UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
				uriBuilder.path(Integer.toString(correction.getUserId())+Integer.toString(correction.getProjectId())+Long.toString(correction.getDate()));
				return Response.created(uriBuilder.build()).entity(correction).build();
			} else {
				return Response.status(Status.CONFLICT).build();
			}
		}
	
}
	
	


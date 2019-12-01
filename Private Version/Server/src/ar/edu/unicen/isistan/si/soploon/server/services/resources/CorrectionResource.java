package ar.edu.unicen.isistan.si.soploon.server.services.resources;

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

import ar.edu.unicen.isistan.si.soploon.server.database.CorrectionDao;
import ar.edu.unicen.isistan.si.soploon.server.database.Database;
import ar.edu.unicen.isistan.si.soploon.server.models.Correction;


// TODO, HACER LOS METODOS HTTP DE ESTA CLASE, SOLO INSERT Y GET, NO HAY UPDATE
// NOTA: EL INSERT DEBERIA TRAER CONSIGO TODA LA LISTA DE ERRORES, NO TIENE SENTIDO INSERTAR DE A UN ERROR

public class CorrectionResource {

	private Database database;
	private CorrectionDao dao;
	private ErrorResource errorResource;
	
	public CorrectionResource (Database database) {
		this.database = database;
		this.dao = new CorrectionDao(this.database);
		this.errorResource = new ErrorResource(this.database);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{time}/")
	public Response getCorrection(@PathParam("user_id") int userId, @PathParam("project_id") int projectId, @PathParam("time") long time) throws Exception {
		System.out.println("simple");
		Correction correction = this.dao.getCorrection(userId,projectId,time);
		if (correction == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok(correction).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCorrections(@PathParam("user_id") int userId, @PathParam("project_id") int projectId) throws Exception {
		ArrayList<Correction> corrections = this.dao.getCorrectionsByProject(userId, projectId);
		if (corrections == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok(corrections).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCorrection(Correction correction, @Context UriInfo uriInfo) throws Exception {
		if (correction == null)
			return Response.status(Response.Status.BAD_REQUEST).build();
		if (this.dao.insert(correction)) {
			UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
			uriBuilder.path(Integer.toString(correction.getUserId())+Integer.toString(correction.getProjectId())+Long.toString(correction.getDate()));
			return Response.created(uriBuilder.build()).entity(correction).build();
		} else {
			return Response.status(Status.CONFLICT).build();
		}
	}
		
	@Path("/{time}/errors")
	public ErrorResource errors(@PathParam("user_id") int userId, @PathParam("project_id") int projectId, @PathParam("time") long time) throws Exception {
		Correction correction = this.dao.getCorrection(userId,projectId,time);
		if (correction == null)
			return null;
		return this.errorResource;
	}
	
}
	
	


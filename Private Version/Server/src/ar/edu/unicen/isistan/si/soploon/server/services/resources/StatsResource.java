package ar.edu.unicen.isistan.si.soploon.server.services.resources;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.edu.unicen.isistan.si.soploon.server.database.Database;
import ar.edu.unicen.isistan.si.soploon.server.database.StatsDao;
import ar.edu.unicen.isistan.si.soploon.server.models.LastUse;
import ar.edu.unicen.isistan.si.soploon.server.models.Stats;

public class StatsResource {
	
	private Database database;
	private StatsDao dao;
	
	public StatsResource (Database database){
		this.database = database;
		this.dao = new StatsDao (this.database);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStats( @QueryParam("date_start") Long dateStart, @QueryParam("date_end") Long dateEnd) throws SQLException {
		Stats stats = new Stats(); 
		stats.setUsersQuantity(this.dao.getUsersQuantity(dateStart,dateEnd));	
		stats.setErrorsQuantity(this.dao.getErrorsQuantity(dateStart,dateEnd));
		stats.setCorrectionsQuantity(this.dao.getCorrectionsQuantity(dateStart, dateEnd));
		stats.setProjectsQuantity(this.dao.getProjectsQuantity(dateStart, dateEnd));
		stats.setErrorsRateElement(this.dao.getErrorsRateElement(dateStart,dateEnd));
		stats.setErrorsTopFive(this.dao.getErrosTopFive(dateStart,dateEnd));
		stats.setAcumCorrections(this.dao.getAcumCorrections(dateStart,dateEnd));
		stats.setLastUse(this.dao.getLastUse());
		return Response.ok(stats).build();
	}
}

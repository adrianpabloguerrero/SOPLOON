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
import ar.edu.unicen.isistan.si.soploon.server.database.ErrorWSDao;
import ar.edu.unicen.isistan.si.soploon.server.models.ErrorWS;
import ar.edu.unicen.isistan.si.soploon.server.providers.Secured;

@Secured
public class ErrorQueryResource {

	private Database database;
	private ErrorWSDao dao;

	public ErrorQueryResource(Database database) {
		this.database = database;
		this.dao = new ErrorWSDao(this.database);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getErrors(@QueryParam("error_id") Integer id, @QueryParam("user_id") Integer userId,
			@QueryParam("date_start") Long dateStart, @QueryParam("date_end") Long dateEnd,
			@QueryParam("rule_id") Integer ruleId) throws SQLException {
		List<ErrorWS> errorsWS = new ArrayList<ErrorWS>();
		if (id != null)
			errorsWS.add(this.dao.getErrorsById(id));
		else {
			if (dateStart == null)
				dateStart = 0L;
			if (dateEnd == null)
				dateEnd = new Date().getTime();
			if (userId != null && ruleId != null) {
				errorsWS.addAll(this.dao.getErrorsByUserAndRuleBetweenDate(userId, ruleId, dateStart, dateEnd));
			} else {
				if (userId != null)
					errorsWS.addAll(this.dao.getErrorsByUserBetweenDate(userId, dateStart, dateEnd));
				else if (ruleId != null)
					errorsWS.addAll(this.dao.getErrorsByRuleBetweenDate(ruleId, dateStart, dateEnd));
				else
					errorsWS.addAll(this.dao.getErrorsBetweenDate(dateStart, dateEnd));
			}
		}
			return Response.ok(errorsWS).build();
	}
}

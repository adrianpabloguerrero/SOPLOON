package ar.edu.unicen.isistan.si.soploon.server.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionCatcher extends Throwable implements ExceptionMapper<Throwable> {

	private static final long serialVersionUID = 4190838269248196681L;

	@Override
	public Response toResponse(Throwable exception) {
		exception.printStackTrace();
		return Response.serverError().build();
	}
}
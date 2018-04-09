package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.hk2.api.Immediate;

@Immediate
@Path("/")
public class Manager {

	private static final String UTF8_CHARSET = ";charset=utf-8";

	private static final String PATH = "./soploon/";
	private static final String LOGS = PATH + "logs/";
	private static final String AUXILIARY_PREDICATES = PATH + "auxiliary_predicates.pl";
	private static final String RULES_PREDICATES = PATH + "rules.pl";
	private static final String RULES = PATH + "rules.xml";

	@GET
	@Path("auxiliary")
	@Produces(MediaType.TEXT_PLAIN + UTF8_CHARSET)
	public Response getAuxiliary() {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(AUXILIARY_PREDICATES));
			String out = new String(encoded, "UTF-8");
			return Response.ok(out).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("rules")
	@Produces(MediaType.TEXT_PLAIN + UTF8_CHARSET)
	public Response getRules() {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(RULES_PREDICATES));
			String out = new String(encoded, "UTF-8");
			return Response.ok(out).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("xml")
	@Produces(MediaType.TEXT_PLAIN + UTF8_CHARSET)
	public Response getXML() {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(RULES));
			String out = new String(encoded, "UTF-8");
			return Response.ok(out).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@POST
	@Path("logs")
	@Consumes({ MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_OCTET_STREAM })
	public Response post(File input) {
		File output = new File(LOGS + System.currentTimeMillis() + ".zip");
		try (FileInputStream src = new FileInputStream(input); FileOutputStream dest = new FileOutputStream(output)) {
			dest.getChannel().transferFrom(src.getChannel(), 0, src.getChannel().size());
		} catch (Exception e) {
			return Response.serverError().build();
		}
		return Response.accepted().build();
	}
}

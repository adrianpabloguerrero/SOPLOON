package ar.edu.unicen.isistan.si.soploon.server.services;

import javax.annotation.PostConstruct;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import org.glassfish.hk2.api.Immediate;

import ar.edu.unicen.isistan.si.soploon.server.database.Database;
import ar.edu.unicen.isistan.si.soploon.server.services.resources.CorrectionResource;
import ar.edu.unicen.isistan.si.soploon.server.services.resources.ErrorQueryResource;
import ar.edu.unicen.isistan.si.soploon.server.services.resources.PredicateResource;
import ar.edu.unicen.isistan.si.soploon.server.services.resources.ProjectResource;
import ar.edu.unicen.isistan.si.soploon.server.services.resources.RuleResource;
import ar.edu.unicen.isistan.si.soploon.server.services.resources.UserResource;

@Immediate
@Path("/")
public class SoploonManager extends Application {

	static final String URL = "jdbc:postgresql://192.168.2.101:5432/soploon";
	static final String USER = "soploon_admin";
	static final String PASS = "soploononon";

	private RuleResource ruleResource;
	private ProjectResource projectResource;
	private UserResource userResource;
	private PredicateResource predicateResorce;
	private CorrectionResource correctionResource;
	private ErrorQueryResource errorQueryResource;
	private Database database;

	@PostConstruct
	public void init() {
		this.database = new Database(URL, USER, PASS);
		this.database.connect();
		this.ruleResource = new RuleResource(this.database);
		this.projectResource = new ProjectResource(this.database);
		this.userResource = new UserResource (this.database);
		this.predicateResorce = new PredicateResource (this.database);
		this.correctionResource = new CorrectionResource(this.database);
		this.errorQueryResource = new ErrorQueryResource (this.database);
	}

	@Path("/rules/")
	public RuleResource getRuleResource() {
		return this.ruleResource;
	}

	/*@Path("/projects/")
	public ProjectResource getProjectResource() {
		return this.projectResource;
	}*/
	
	@Path("/users/")
	public UserResource getUserResource() {
		return this.userResource;
	}
	
	@Path("/predicates/")
	public PredicateResource getPredicateResource() {
		return this.predicateResorce;
	}

	@Path("/errors/")
	public ErrorQueryResource getErrorQueryResource() {
		return this.errorQueryResource;
	}
	
	/*@Path("/correction/")
	public CorrectionResource getCorrectionResource() {
		return this.correctionResource;
	}*/
	
	
}

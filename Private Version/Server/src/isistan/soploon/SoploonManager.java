package isistan.soploon;

import javax.annotation.PostConstruct;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import org.glassfish.hk2.api.Immediate;

import isistan.soploon.database.Database;
import isistan.soploon.services.resources.CorrectionResource;
import isistan.soploon.services.resources.PredicateResource;
import isistan.soploon.services.resources.ProjectResource;
import isistan.soploon.services.resources.RuleResource;
import isistan.soploon.services.resources.users.UserResource;

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
	}

	@Path("/rules/")
	public RuleResource getRuleResource() {
		return this.ruleResource;
	}

	@Path("/projects/")
	public ProjectResource getProjectResource() {
		return this.projectResource;
	}
	
	@Path("/users/")
	public UserResource getUserResource() {
		return this.userResource;
	}
	
	@Path("/predicate/")
	public PredicateResource getPredicateResource() {
		return this.predicateResorce;
	}

	@Path("/correction/")
	public CorrectionResource getCorrectionResource() {
		return this.correctionResource;
	}
	
	
}

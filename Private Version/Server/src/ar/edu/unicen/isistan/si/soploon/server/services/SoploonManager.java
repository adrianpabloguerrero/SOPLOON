package ar.edu.unicen.isistan.si.soploon.server.services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

import org.glassfish.hk2.api.Immediate;

import ar.edu.unicen.isistan.si.soploon.server.database.Database;
import ar.edu.unicen.isistan.si.soploon.server.providers.AuthenticationFilter;
import ar.edu.unicen.isistan.si.soploon.server.services.resources.AuthenticationResource;
import ar.edu.unicen.isistan.si.soploon.server.services.resources.ErrorQueryResource;
import ar.edu.unicen.isistan.si.soploon.server.services.resources.PredicateResource;
import ar.edu.unicen.isistan.si.soploon.server.services.resources.RuleResource;
import ar.edu.unicen.isistan.si.soploon.server.services.resources.StatsResource;
import ar.edu.unicen.isistan.si.soploon.server.services.resources.UserResource;

@Immediate
@Path("/")
public class SoploonManager extends Application {
	
	@Context
	ServletContext context;
	
	private RuleResource ruleResource;
	private UserResource userResource;
	private PredicateResource predicateResorce;
	private ErrorQueryResource errorQueryResource;
	private StatsResource statsResource;
	private AuthenticationResource authenticationResource;
	private Database database;

	@PostConstruct
	public void init() {
		String path = context.getInitParameter("database_path");
		String user = context.getInitParameter("database_user");
		String password = context.getInitParameter("database_password");
		String jwtSecret = context.getInitParameter("jwt_secret").trim();
	
		AuthenticationFilter.KEY = jwtSecret;
		this.database = new Database(path, user, password);
		this.database.connect();
		this.ruleResource = new RuleResource(this.database);
		this.userResource = new UserResource (this.database);
		this.predicateResorce = new PredicateResource (this.database);
		this.errorQueryResource = new ErrorQueryResource (this.database);
		this.statsResource = new StatsResource (this.database);
		this.authenticationResource = new AuthenticationResource(this.database, jwtSecret);
	}

	@Path("/rules/")
	public RuleResource getRuleResource() {
		return this.ruleResource;
	}

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
	
	@Path("/stats/")
	public StatsResource getStatsResource () {
		return this.statsResource;
	}
	
	@Path("/authentication")
	public AuthenticationResource getAuthenticationResource () {
		return this.authenticationResource;
	}
	
}

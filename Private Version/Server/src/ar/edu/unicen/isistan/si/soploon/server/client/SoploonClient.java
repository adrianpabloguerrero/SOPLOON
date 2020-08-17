package ar.edu.unicen.isistan.si.soploon.server.client;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import ar.edu.unicen.isistan.si.soploon.server.models.Correction;
import ar.edu.unicen.isistan.si.soploon.server.models.Predicate;
import ar.edu.unicen.isistan.si.soploon.server.models.Project;
import ar.edu.unicen.isistan.si.soploon.server.models.Rule;
import ar.edu.unicen.isistan.si.soploon.server.models.User;
import ar.edu.unicen.isistan.si.soploon.server.models.Error;

public class SoploonClient {

	private static final String USERS = "users";
	private static final String PROJECTS = "projects";
	private static final String RULES = "rules";
	private static final String VERSIONS = "versions";
	private static final String PREDICATES = "predicates";
	private static final String CORRECTIONS = "corrections";
	private static final String ERRORS = "errors";
	private static final String AUTH = "authentication";

	private String basePath;
	private User user;
	private Client client;
	private String accessToken;
	
	/**
	 * Crea un cliente para la API de soploon alojada en la URL indicada
	 * @param basePath URL base de la API 
	 */
	public SoploonClient(String basePath) {
		this.basePath = basePath;
		ClientConfig config = new ClientConfig().register(new GsonClientProvider());
		this.client = ClientBuilder.newClient(config);
	}

	/**
	 * Crea un cliente para la API de soploon alojada en la URL indicada y con el usuario indicado
	 * @param basePath URL base de la API
	 * @param user Usuario con el cual identificarse en la API
	 */
	public SoploonClient(String basePath, User user) {
		this.basePath = basePath;
		ClientConfig config = new ClientConfig().register(new GsonClientProvider());
		this.client = ClientBuilder.newClient(config);
		this.user = user;
	}
	
	public boolean authenticate() {
		if (this.user != null) {
			try {
				WebTarget target = client.target(this.basePath).path(AUTH);
				Form form = new Form();
			    form.param("userId", String.valueOf(user.getId()));
			    form.param("userName", user.getName());
			    form.param("password", user.getPassword());
				Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
				if (response.getStatus() == Response.Status.OK.getStatusCode()) {
					this.accessToken = response.readEntity(String.class);
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		return false;
	}
	
	/**
	 * Obtiene el listado de todos los usuarios.
	 * @return Lista de usuarios
	 */
	public ArrayList<User> getUsers() {
		try {
			WebTarget target = client.target(this.basePath).path(USERS);					
			Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).get();
			if (response.getStatus() == Response.Status.OK.getStatusCode())
				return response.readEntity(new GenericType<ArrayList<User>>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Busca un usuario por ID.
	 * @param userId ID del usuario
	 * @return Usuario
	 */
	public User getUser(int userId) {
		try {
			WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(userId));
			Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).get();
			if (response.getStatus() == Response.Status.OK.getStatusCode())
				return response.readEntity(User.class);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Crea un usuario con los parametros indicados. Retorna null si no se pudo crear el usuario.
	 * @param user Usuario que se desea crear
	 * @return Usuario creado con el ID asignado.
	 */
	public User postUser(User user) {
		try {
			WebTarget target = client.target(this.basePath).path(USERS);
			Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).post(Entity.entity(user, MediaType.APPLICATION_JSON));
			if (response.getStatus() == Response.Status.CREATED.getStatusCode())
				return response.readEntity(User.class);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Lista todos los proyectos del usuario especificado.
	 * @param user Usuario en cuestion
	 * @return Lista de proyectos
	 */
	public ArrayList<Project> getProjects(User user) {
		return this.getProjects(user.getId());
	}
	
	/**
	 * Lista todos los proyectos del usuario especificado.
	 * @param userId ID del usuario en cuestion
	 * @return Lista de proyectos
	 */
	public ArrayList<Project> getProjects(long userId) {
		try {
			WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(userId)).path(PROJECTS);
			Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).get();
			if (response.getStatus() == Response.Status.OK.getStatusCode())
				return response.readEntity(new GenericType<ArrayList<Project>>() {});		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    	
	/**
	 * Busca un proyecyo por ID.
	 * @param userId ID del usuario propietario del proyecto
	 * @param projectId ID del proyecto buscado
	 * @return Proyecto
	 */
	public Project getProject(long userId, long projectId) {
		try {
			WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(userId)).path(PROJECTS).path(String.valueOf(projectId));
			Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).get();
			if (response.getStatus() == Response.Status.OK.getStatusCode())
				return response.readEntity(Project.class);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Busca un proyecyo por ID.
	 * @param user Usuario propietario del proyecto
	 * @param projectId ID del proyecto buscado
	 * @return Proyecto
	 */
	public Project getProject(User user, int projectId) {
		return this.getProject(user.getId(),projectId);
	}
	
	/**
	 * Crea un proyecto con los parametros indicados. Retorna null si no se pudo crear el proyecto.
	 * @param project Projecto que se desea crear
	 * @return Proyecto creado con el ID asignado.
	 */	
	public Project postProject(Project project) {
		try {
			WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(project.getUserId())).path(PROJECTS);
			Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).post(Entity.entity(project, MediaType.APPLICATION_JSON));
			if (response.getStatus() == Response.Status.CREATED.getStatusCode())
				return response.readEntity(Project.class);		
			else if (response.getStatus() == Response.Status.CONFLICT.getStatusCode())
				return project;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Correction> getCorrections(int userId, int projectId) {
		try{
			WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(userId)).path(PROJECTS).path(String.valueOf(projectId)).path(CORRECTIONS);
			Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).get();
			if (response.getStatus() == Response.Status.OK.getStatusCode())
				return response.readEntity(new GenericType<ArrayList<Correction>>() {});		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Correction getCorrection(int userId, int projectId, long correctionTime) {
		try {
			WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(userId)).path(PROJECTS).path(String.valueOf(projectId)).path(CORRECTIONS).path(String.valueOf(correctionTime));
			Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).get();
			if (response.getStatus() == Response.Status.OK.getStatusCode())
				return response.readEntity(Correction.class);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Crea una correccion con los parametros indicados. Retorna null si no se pudo crear la correccion.
	 * @param correccion Correccion que se desea crear
	 * @return Correccion creada.
	 */	
	public Correction postCorrection(Correction correction) {
		try {
			WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(correction.getUserId())).path(PROJECTS).path(String.valueOf(correction.getProjectId())).path(CORRECTIONS);		
			Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).post(Entity.entity(correction, MediaType.APPLICATION_JSON));
			if (response.getStatus() == Response.Status.CREATED.getStatusCode())
				return response.readEntity(Correction.class);		
			else if (response.getStatus() == Response.Status.CONFLICT.getStatusCode())
				return correction;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ArrayList<Error> getErrors(long userId, long projectId, long time) {
		try {
			WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(userId)).path(PROJECTS).path(String.valueOf(projectId)).path(CORRECTIONS).path(String.valueOf(time)).path(ERRORS);
			Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).get();
			if (response.getStatus() == Response.Status.OK.getStatusCode())
				return response.readEntity(new GenericType<ArrayList<Error>>() {});		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Error getError(int userId, int projectId, long time, int errorId) {
		try{
			WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(userId)).path(PROJECTS).path(String.valueOf(projectId)).path(CORRECTIONS).path(String.valueOf(time)).path(ERRORS).path(String.valueOf(errorId));
			Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).get();
			if (response.getStatus() == Response.Status.OK.getStatusCode())
				return response.readEntity(Error.class);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Crea una correccion con los parametros indicados. Retorna null si no se pudo crear la correccion.
	 * @param correccion Correccion que se desea crear
	 * @return Correccion creada.
	 */	
	public ArrayList<Error> postErrors(ArrayList<Error> errors) {
		if (errors.isEmpty())
			return errors;
		
		try {
			Error error = errors.get(0);
			ArrayList<Error> alreadyStored = this.getErrors(error.getUserId(), error.getProjectId(), error.getDate());
			if (alreadyStored == null || !alreadyStored.isEmpty())
				return alreadyStored;
			
			WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(error.getUserId())).path(PROJECTS).path(String.valueOf(error.getProjectId())).path(CORRECTIONS).path(String.valueOf(error.getDate())).path(ERRORS);
			Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).post(Entity.entity(errors, MediaType.APPLICATION_JSON));
			
			if (response.getStatus() == Response.Status.CREATED.getStatusCode())
				return response.readEntity(new GenericType<ArrayList<Error>>(){});		
			else if (response.getStatus() == Response.Status.CONFLICT.getStatusCode())
				return errors;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Lista todas las reglas activas.
	 * @return Lista de reglas
	 */
	public ArrayList<Rule> getRules() {
		try {
			WebTarget target = client.target(this.basePath).path(RULES);
			Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).get();
			if (response.getStatus() == Response.Status.OK.getStatusCode())
				return response.readEntity(new GenericType<ArrayList<Rule>>() {});		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Rule getRule(int ruleId) {
		try {
			WebTarget target = client.target(this.basePath).path(RULES).path(String.valueOf(ruleId));
			Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).get();
			if (response.getStatus() == Response.Status.OK.getStatusCode())
				return response.readEntity(Rule.class);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Rule> getRuleHistory(int ruleId) {
		try {
			WebTarget target = client.target(this.basePath).path(RULES).path(String.valueOf(ruleId)).path(VERSIONS);
			Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).get();
			if (response.getStatus() == Response.Status.OK.getStatusCode())
				return response.readEntity(new GenericType<ArrayList<Rule>>() {});		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Predicate> getPredicates() {
		WebTarget target = client.target(this.basePath).path(PREDICATES);
		Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(new GenericType<ArrayList<Predicate>>() {});		
		else
			return null;
	}
	
	public Predicate getPredicate(int predicateId) {
		try {
			WebTarget target = client.target(this.basePath).path(PREDICATES).path(String.valueOf(predicateId));
			Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).get();
			if (response.getStatus() == Response.Status.OK.getStatusCode())
				return response.readEntity(Predicate.class);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Predicate> getPredicateHistory(int predicateId) {
		try {
			WebTarget target = client.target(this.basePath).path(PREDICATES).path(String.valueOf(predicateId)).path(VERSIONS);
			Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken).get();
			if (response.getStatus() == Response.Status.OK.getStatusCode())
				return response.readEntity(new GenericType<ArrayList<Predicate>>() {});		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

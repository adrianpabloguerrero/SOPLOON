package ar.edu.unicen.isistan.si.soploon.server.client;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import ar.edu.unicen.isistan.si.soploon.server.models.Correction;
import ar.edu.unicen.isistan.si.soploon.server.models.Predicate;
import ar.edu.unicen.isistan.si.soploon.server.models.Project;
import ar.edu.unicen.isistan.si.soploon.server.models.Rule;
import ar.edu.unicen.isistan.si.soploon.server.models.User;
import ar.edu.unicen.isistan.si.soploon.server.models.Error;
import ar.edu.unicen.isistan.si.soploon.server.providers.GsonProvider;

public class SoploonClient {

	private static final String USERS = "users";
	private static final String PROJECTS = "projects";
	private static final String RULES = "rules";
	private static final String VERSIONS = "versions";
	private static final String PREDICATES = "predicates";
	private static final String CORRECTIONS = "corrections";
	private static final String ERRORS = "errors";

	private String basePath;

	private Client client;

	/**
	 * Crea un cliente para la API de soploon alojada en la URL indicada
	 * @param basePath URL base de la API 
	 */
	public SoploonClient(String basePath) {
		this.basePath = basePath;
		ClientConfig config = new ClientConfig().register(new GsonProvider());
		this.client = ClientBuilder.newClient(config);
	}

	/**
	 * Obtiene el listado de todos los usuarios.
	 * @return Lista de usuarios
	 */
	public ArrayList<User> getUsers() {
		WebTarget target = client.target(this.basePath).path(USERS);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(new GenericType<ArrayList<User>>() {});
		else
			return null;
	}

	/**
	 * Busca un usuario por ID.
	 * @param userId ID del usuario
	 * @return Usuario
	 */
	public User getUser(int userId) {
		WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(userId));
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(User.class);		
		else
			return null;
	}

	/**
	 * Crea un usuario con los parametros indicados. Retorna null si no se pudo crear el usuario.
	 * @param user Usuario que se desea crear
	 * @return Usuario creado con el ID asignado.
	 */
	public User postUser(User user) {
		WebTarget target = client.target(this.basePath).path(USERS);
		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(user, MediaType.APPLICATION_JSON));
		if (response.getStatus() == Response.Status.CREATED.getStatusCode())
			return response.readEntity(User.class);		
		else
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
	public ArrayList<Project> getProjects(int userId) {
		WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(userId)).path(PROJECTS);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(new GenericType<ArrayList<Project>>() {});		
		else
			return null;
	}
    	
	/**
	 * Busca un proyecyo por ID.
	 * @param userId ID del usuario propietario del proyecto
	 * @param projectId ID del proyecto buscado
	 * @return Proyecto
	 */
	public Project getProject(int userId, int projectId) {
		WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(userId)).path(PROJECTS).path(String.valueOf(projectId));
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(Project.class);		
		else
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
		WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(project.getUserId())).path(PROJECTS);
		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(project, MediaType.APPLICATION_JSON));
		if (response.getStatus() == Response.Status.CREATED.getStatusCode())
			return response.readEntity(Project.class);		
		else if (response.getStatus() == Response.Status.CONFLICT.getStatusCode())
			return project;
		return null;
	}
	
	public ArrayList<Correction> getCorrections(int userId, int projectId) {
		WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(userId)).path(PROJECTS).path(String.valueOf(projectId)).path(CORRECTIONS);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(new GenericType<ArrayList<Correction>>() {});		
		else
			return null;
	}
	
	public Correction getCorrection(int userId, int projectId, long correctionTime) {
		WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(userId)).path(PROJECTS).path(String.valueOf(projectId)).path(CORRECTIONS).path(String.valueOf(correctionTime));
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(Correction.class);		
		else
			return null;
	}
	
	/**
	 * Crea una correccion con los parametros indicados. Retorna null si no se pudo crear la correccion.
	 * @param correccion Correccion que se desea crear
	 * @return Correccion creada.
	 */	
	public Correction postCorrection(Correction correction) {
		WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(correction.getUserId())).path(PROJECTS).path(String.valueOf(correction.getProjectId())).path(CORRECTIONS);		
		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(correction, MediaType.APPLICATION_JSON));
		if (response.getStatus() == Response.Status.CREATED.getStatusCode())
			return response.readEntity(Correction.class);		
		else if (response.getStatus() == Response.Status.CONFLICT.getStatusCode())
			return correction;
		return null;
	}
	
	
	public ArrayList<Error> getErrors(int userId, int projectId, long time) {
		WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(userId)).path(PROJECTS).path(String.valueOf(projectId)).path(CORRECTIONS).path(String.valueOf(time)).path(ERRORS);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(new GenericType<ArrayList<Error>>() {});		
		else
			return null;
	}
	
	public Error getError(int userId, int projectId, long time, int errorId) {
		WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(userId)).path(PROJECTS).path(String.valueOf(projectId)).path(CORRECTIONS).path(String.valueOf(time)).path(ERRORS).path(String.valueOf(errorId));
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(Error.class);		
		else
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
		
		Error error = errors.get(0);
		ArrayList<Error> alreadyStored = this.getErrors(error.getUserId(), error.getProjectId(), error.getDate());
		if (alreadyStored == null || !alreadyStored.isEmpty())
			return alreadyStored;
		
		WebTarget target = client.target(this.basePath).path(USERS).path(String.valueOf(error.getUserId())).path(PROJECTS).path(String.valueOf(error.getProjectId())).path(CORRECTIONS).path(String.valueOf(error.getDate())).path(ERRORS);
		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(errors, MediaType.APPLICATION_JSON));
		
		if (response.getStatus() == Response.Status.CREATED.getStatusCode())
			return response.readEntity(new GenericType<ArrayList<Error>>(){});		
		else if (response.getStatus() == Response.Status.CONFLICT.getStatusCode())
			return errors;
		
		return null;
	}
	
	/**
	 * Lista todas las reglas activas.
	 * @return Lista de reglas
	 */
	public ArrayList<Rule> getRules() {
		WebTarget target = client.target(this.basePath).path(RULES);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(new GenericType<ArrayList<Rule>>() {});		
		else
			return null;
	}
	
	public Rule getRule(int ruleId) {
		WebTarget target = client.target(this.basePath).path(RULES).path(String.valueOf(ruleId));
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(Rule.class);		
		else
			return null;
	}
	
	public ArrayList<Rule> getRuleHistory(int ruleId) {
		WebTarget target = client.target(this.basePath).path(RULES).path(String.valueOf(ruleId)).path(VERSIONS);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(new GenericType<ArrayList<Rule>>() {});		
		else
			return null;
	}
	
	public ArrayList<Predicate> getPredicates() {
		WebTarget target = client.target(this.basePath).path(PREDICATES);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(new GenericType<ArrayList<Predicate>>() {});		
		else
			return null;
	}
	
	public Predicate getPredicate(int predicateId) {
		WebTarget target = client.target(this.basePath).path(PREDICATES).path(String.valueOf(predicateId));
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(Predicate.class);		
		else
			return null;
	}
	
	public ArrayList<Predicate> getPredicateHistory(int predicateId) {
		WebTarget target = client.target(this.basePath).path(PREDICATES).path(String.valueOf(predicateId)).path(VERSIONS);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(new GenericType<ArrayList<Predicate>>() {});		
		else
			return null;
	}
	
	
}

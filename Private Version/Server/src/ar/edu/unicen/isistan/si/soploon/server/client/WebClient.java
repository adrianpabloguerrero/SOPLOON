package ar.edu.unicen.isistan.si.soploon.server.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import ar.edu.unicen.isistan.si.soploon.server.models.Project;
import ar.edu.unicen.isistan.si.soploon.server.models.User;
import ar.edu.unicen.isistan.si.soploon.server.providers.GsonProvider;

public class WebClient {

	private static final String USERS = "users/";
	private static final String PROJECTS = "projects/";

	private String basePath;

	private Client client;

	public WebClient(String basePath) {
		this.basePath = basePath;
		ClientConfig config = new ClientConfig().register(new GsonProvider());
		this.client = ClientBuilder.newClient(config);

	}

	public List<User> getUsers() {
		WebTarget target = client.target(this.basePath).path(USERS);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(new GenericType<List<User>>() {});
		else
			return null;
	}

	public User getUser(int userId) {
		WebTarget target = client.target(this.basePath).path(USERS + userId);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(User.class);		
		else
			return null;
	}

	public User postUser(User user) {
		WebTarget target = client.target(this.basePath).path(USERS);
		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(user, MediaType.APPLICATION_JSON));
		if (response.getStatus() == Response.Status.CREATED.getStatusCode())
			return response.readEntity(User.class);		
		else
			return null;
	}

	public List<Project> getProjects(User user) {
		return this.getProjects(user.getId());
	}
	
	public List<Project> getProjects(int userId) {
		WebTarget target = client.target(this.basePath).path(USERS + userId).path(PROJECTS);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(new GenericType<List<Project>>() {});		
		else
			return null;
	}
    	
	public List<Project> getProject(int userId, int projectId) {
		WebTarget target = client.target(this.basePath).path(USERS + userId).path(PROJECTS);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Response.Status.OK.getStatusCode())
			return response.readEntity(new GenericType<List<Project>>() {});		
		else
			return null;
	}
	
	public List<Project> getProject(User user, int projectId) {
		return this.getProject(user.getId(),projectId);
	}
	
	
}

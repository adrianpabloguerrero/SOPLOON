package ar.edu.unicen.isistan.si.teachingassistant.plugin.storage;

import java.util.HashMap;

import ar.edu.unicen.isistan.si.soploon.server.models.Project;
import ar.edu.unicen.isistan.si.soploon.server.models.User;

public class Data {
	
	private User user;
	private HashMap<String, Long> projects;

	public Data() {
		this.user = null;
		this.projects = new HashMap<>();
	}

	public boolean hasUser() {
		return this.user != null;
	}
	
	public long getUserId() {
		return this.user != null ? this.user.getId() : 0;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}
	
	public void addProject(String projectName, long projectId) {
		this.projects.put(projectName, projectId);
	}

	public Long getProjectId(Project project) {
		return this.projects.get(project.getName());
	}	
	
	public boolean hasProjectId(Project project) {
		return this.projects.containsKey(project.getName());
	}	
	
}

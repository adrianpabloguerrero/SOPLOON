package ar.edu.unicen.isistan.si.soploon.plugin.storage;

import java.util.HashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;

public class UserData {

	private static final int NO_DATA = -1;
	private int userId;
	private HashMap<String, Integer> projects;

	public UserData() {
		this.userId = NO_DATA;
		this.projects = new HashMap<String,Integer>();
	}

	public boolean hasUserId() {
		return this.userId != NO_DATA;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void addProject(IJavaProject javaProject, int projectsId) {
		this.projects.put(this.generateKey(javaProject), projectsId);
	}

	public void getProjectId(IJavaProject javaProject, int projectsId) {
		this.projects.put(this.generateKey(javaProject), projectsId);
	}	
	
	private String generateKey(IJavaProject javaProject) {
		IProject project = javaProject.getProject();
		return project.getLocationURI() + " " + project.getName();
	}
	
}

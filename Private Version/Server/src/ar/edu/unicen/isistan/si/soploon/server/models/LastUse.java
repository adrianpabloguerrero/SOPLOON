package ar.edu.unicen.isistan.si.soploon.server.models;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

public class LastUse {

	@Expose 
	private String nameProject;
	@Expose 
	private String nameUser; 
	@Expose 
	private long date;
	@Expose
	ArrayList<ErrorStatsElement> errors;
	
	public String getNameProject() {
		return nameProject;
	}
	public void setNameProject(String nameProject) {
		this.nameProject = nameProject;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public ArrayList<ErrorStatsElement> getErrors() {
		return errors;
	}
	public void setErrors(ArrayList<ErrorStatsElement> errors) {
		this.errors = errors;
	}
	
}

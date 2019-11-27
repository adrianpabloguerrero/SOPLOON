package ar.edu.unicen.isistan.si.soploon.server.models;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

public class Error {

	@Expose
	private int id;
	@Expose
	private int idProject;
	@Expose
	private int idUser;
	@Expose
	private long date;
	@Expose
	private int idRule;
	@Expose
	private int versionRule;
	@Expose
	private ArrayList <CodeLocation> codeLocation;
	@Expose
	private ArrayList <Integer> representationLocation;
	@Expose
	private int reviewed;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdProject() {
		return idProject;
	}
	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public int getIdRule() {
		return idRule;
	}
	public void setIdRule(int idRule) {
		this.idRule = idRule;
	}
	public int getVersionRule() {
		return versionRule;
	}
	public void setVersionRule(int versionRule) {
		this.versionRule = versionRule;
	}
	public ArrayList<CodeLocation> getCodeLocation() {
		return codeLocation;
	}
	public void setCodeLocation(ArrayList<CodeLocation> codeLocation) {
		this.codeLocation = codeLocation;
	}
	public ArrayList<Integer> getRepresentationLocation() {
		return representationLocation;
	}
	public void setRepresentationLocation(ArrayList<Integer> representationLocation) {
		this.representationLocation = representationLocation;
	}
	public int getReviewed() {
		return reviewed;
	}
	public void setReviewed(int reviewed) {
		this.reviewed = reviewed;
	}
	
}



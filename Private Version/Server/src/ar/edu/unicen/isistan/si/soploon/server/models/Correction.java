package ar.edu.unicen.isistan.si.soploon.server.models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Correction {
	
	@Expose
	private int userId;
	@Expose
	private int projectId;
	@Expose
	private long date;	
	@Expose
	private ArrayList<SourceCode> code;
	@Expose
	private ArrayList<String> representation;
	@Expose
	private String versionSoploon;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public ArrayList<SourceCode> getCode() {
		return code;
	}
	public void setCode(ArrayList<SourceCode> code) {
		this.code = code;
	}
	public ArrayList<String> getRepresentation() {
		return representation;
	}
	public void setRepresentation(ArrayList<String> representation) {
		this.representation = representation;
	}
	public String getVersionSoploon() {
		return versionSoploon;
	}
	public void setVersionSoploon(String versionSoploon) {
		this.versionSoploon = versionSoploon;
	}
	
	
	
	
}
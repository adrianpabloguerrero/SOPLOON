package isistan.soploon.services.resources.error;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

public class Error {

	@Expose
	private int id;
	@Expose
	private int userId;
	@Expose
	private int projectId;
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



package isistan.soploon.models.error;

import java.util.ArrayList;

public class Error {

	private int id;
	private int userId;
	private int projectId;
	private long date;
	private int idRule;
	private int versionRule;
	private ArrayList <CodeLocation> codeLocation;
	private ArrayList <Integer> representationLocation;


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

	
}



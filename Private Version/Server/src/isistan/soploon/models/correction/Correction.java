package isistan.soploon.models.correction;

import java.util.ArrayList;

public class Correction {
	
	private int userId;
	private int projectId;
	private long date;
	private SourceCode code;
	private ArrayList<String> representation;
	private String version;
	
	
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
	public SourceCode getCode() {
		return code;
	}
	public void setCode(SourceCode code) {
		this.code = code;
	}
	public ArrayList<String> getRepresentation() {
		return representation;
	}
	public void setRepresentation(ArrayList<String> representation) {
		this.representation = representation;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
}
package isistan.soploon.models.project;

import java.util.List;

public class Project {

	
	private int userId;
	private long date;
	private List<JavaFile> code;
	private String representation;
	private String soploonVersion;
	
	public Project () {
		
	}
	
	public int getUserId() {
		return userId;
	}
	public void setIdUser(int userId) {
		this.userId = userId;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public List <JavaFile> getCode() {
		return code;
	}
	public void setCodigo(List<JavaFile> code) {
		this.code = code;
	}
	public String getRepresentation() {
		return representation;
	}
	public void setRepresentation(String representation) {
		this.representation = representation;
	}
	public String getSoploonVersion() {
		return soploonVersion;
	}
	public void setSoploonVersion(String soploonVersion) {
		this.soploonVersion = soploonVersion;
	}
	
	
	
	
	
}

package isistan.soploon.models.correction;

public class Correction {
	
	private int userId;
	private int projectId;
	private long date;
	private String code;
	private String correction;
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCorrection() {
		return correction;
	}
	public void setCorrection(String correction) {
		this.correction = correction;
	}

}



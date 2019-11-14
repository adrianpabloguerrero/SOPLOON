package isistan.soploon.services.resources.projects;

import com.google.gson.annotations.Expose;

public class Project {

	@Expose
	private int userId;
	@Expose
	private int id;
	@Expose
	private String name;

	public Project() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setIdUser(int userId) {
		this.userId = userId;
	}
	
	public boolean check() {
		return this.name != null;
	}

}

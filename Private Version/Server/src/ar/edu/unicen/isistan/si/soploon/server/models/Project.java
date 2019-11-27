package ar.edu.unicen.isistan.si.soploon.server.models;

import com.google.gson.annotations.Expose;

public class Project {

	@Expose
	private int id;
	@Expose
	private int userId;
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

	@Override
	public String toString() {
		return "Project [id=" + id + ", userId=" + userId + ", name=" + name + "]";
	}

}

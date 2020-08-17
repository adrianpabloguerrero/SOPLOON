package ar.edu.unicen.isistan.si.soploon.server.models;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.gson.annotations.Expose;

public class User {

	public enum Role {
		profesor, student;
	}
	
	@Expose
	private long id;
	@Expose
	private long creationDate;
	@Expose
	private String name;
	@Expose
	private Role role;
	@Expose(serialize = false, deserialize = true)
	private String password;
	
	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean check() {
		return this.role != null && this.password != null;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", creationDate=" + creationDate + ", name=" + name + ", role=" + role + ", password=" + password + "]";
	}

	public void prepare() {
		this.password = DigestUtils.sha256Hex(this.password);
	}

}

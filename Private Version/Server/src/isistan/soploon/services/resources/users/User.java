package isistan.soploon.services.resources.users;

import com.google.gson.annotations.Expose;

public class User {

	public enum Role {
		profesor, student;
	}
	
	@Expose
	private int id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", creationDate=" + creationDate + ", name=" + name + ", role=" + role + ", password="
				+ password + "]";
	}

	public boolean check() {
		return this.role != null && (this.role != Role.profesor || this.password != null);
	}

}

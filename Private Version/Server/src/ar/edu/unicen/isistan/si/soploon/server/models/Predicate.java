package ar.edu.unicen.isistan.si.soploon.server.models;

import com.google.gson.annotations.Expose;

public class Predicate {

	@Expose
	private int id;
	@Expose
	private int version;
	@Expose
	private String name;
	@Expose
	private String description;
	@Expose
	private String code;
	@Expose
	private boolean activated;
	
	public Predicate (){
		this.id = 0;
		this.version = 1;
		this.name = null;
		this.description = null;
		this.code = null;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public boolean getActivated() {
		return activated;
	}
	
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	@Override
	public String toString() {
		return "Predicate [id=" + id + ", version=" + version + ", name=" + name + ", description=" + description
				+ ", code=" + code + ", activated=" + activated + "]";
	}

}

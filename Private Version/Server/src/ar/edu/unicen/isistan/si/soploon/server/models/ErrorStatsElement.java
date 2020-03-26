package ar.edu.unicen.isistan.si.soploon.server.models;

import com.google.gson.annotations.Expose;

public class ErrorStatsElement {

	@Expose
	private String name;
	@Expose
	private float y;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
}

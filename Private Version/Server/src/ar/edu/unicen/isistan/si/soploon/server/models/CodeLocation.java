package ar.edu.unicen.isistan.si.soploon.server.models;

import com.google.gson.annotations.Expose;

public class CodeLocation {
	
	@Expose
	private String Path;
	@Expose
	private String startChar;
	@Expose
	private String endChar;
	
	
	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}
	public String getStartChar() {
		return startChar;
	}
	public void setStartChar(String startChar) {
		this.startChar = startChar;
	}
	public String getEndChar() {
		return endChar;
	}
	public void setEndChar(String endChar) {
		this.endChar = endChar;
	}

	
	
}

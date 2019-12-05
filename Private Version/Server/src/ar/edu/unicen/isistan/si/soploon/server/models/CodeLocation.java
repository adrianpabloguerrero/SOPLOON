package ar.edu.unicen.isistan.si.soploon.server.models;

import com.google.gson.annotations.Expose;

public class CodeLocation {

	@Expose
	private String path;
	@Expose
	private String startChar;
	@Expose
	private String endChar;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
	
	@Override
	public boolean equals(Object o) {
	        if (this == o) {
	            return true;
	        }
	        if (o == null || getClass() != o.getClass()) {
	            return false;
	        }
	        CodeLocation codeLocation = (CodeLocation) o;
	        return	this.path.equals(codeLocation.getPath()) &&
	        		this.startChar.equals(codeLocation.getStartChar()) &&
	        		this.endChar.equals(codeLocation.getEndChar());
		}

	@Override
	public String toString() {
		return "CodeLocation [path=" + path + ", startChar=" + startChar + ", endChar=" + endChar + "]";
	}

}

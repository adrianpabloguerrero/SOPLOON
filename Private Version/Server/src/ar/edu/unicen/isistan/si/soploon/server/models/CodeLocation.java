package ar.edu.unicen.isistan.si.soploon.server.models;

import com.google.gson.annotations.Expose;

public class CodeLocation {

	@Expose
	private String path;
	@Expose
	private int startChar;
	@Expose
	private int endChar;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getStartChar() {
		return startChar;
	}

	public void setStartChar(int startChar) {
		this.startChar = startChar;
	}

	public int getEndChar() {
		return endChar;
	}

	public void setEndChar(int endChar) {
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
	        		this.startChar == codeLocation.getStartChar() &&
	        		this.endChar == codeLocation.getEndChar();
		}

	@Override
	public String toString() {
		return "CodeLocation [path=" + path + ", startChar=" + startChar + ", endChar=" + endChar + "]";
	}

}

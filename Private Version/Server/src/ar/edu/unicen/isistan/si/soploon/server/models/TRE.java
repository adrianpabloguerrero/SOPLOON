package ar.edu.unicen.isistan.si.soploon.server.models;

import java.util.HashMap;

import com.google.gson.annotations.Expose;

public class TRE {

	@Expose
	private HashMap <String,TREElement> data = new HashMap <String,TREElement>();

	public HashMap<String, TREElement> getData() {
		return data;
	}

	public void setData(HashMap<String, TREElement> data) {
		this.data = data;
	}
	
}

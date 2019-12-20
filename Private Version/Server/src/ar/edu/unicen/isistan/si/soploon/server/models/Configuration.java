package ar.edu.unicen.isistan.si.soploon.server.models;

import java.util.HashMap;

import com.google.gson.annotations.Expose;

public class Configuration {

	@Expose
	private String version;
	@Expose
	private HashMap<Integer, Integer> rules;
	@Expose
	private HashMap<Integer, Integer> predicates;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public HashMap<Integer, Integer> getRules() {
		return rules;
	}

	public void setRules(HashMap<Integer, Integer> rules) {
		this.rules = rules;
	}

	public HashMap<Integer, Integer> getPredicates() {
		return predicates;
	}

	public void setPredicates(HashMap<Integer, Integer> predicates) {
		this.predicates = predicates;
	}

}
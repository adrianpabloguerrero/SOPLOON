package ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.analyzer;

import ar.edu.unicen.isistan.si.soploon.server.models.Predicate;

public class PrologPredicate {

	private Predicate predicate;
	
	public PrologPredicate(Predicate predicate) {
		this.predicate = predicate;
	}
	
	public String getName() {
		return this.predicate.getName();
	}

	public String getDescription() {
		return this.predicate.getDescription();
	}

	public String getCode() {
		return this.predicate.getCode();
	}

	public boolean isActivated() {
		return this.predicate.getActivated();
	}
	
	public int getVersion() {
		return this.predicate.getVersion();
	}
	
	public int getId() {
		return this.predicate.getId();
	}
}

package ar.edu.unicen.isistan.si.teachingassistant.plugin.storage;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unicen.isistan.si.soploon.server.models.Predicate;
import ar.edu.unicen.isistan.si.soploon.server.models.Rule;

public class Configuration {

	private List<Rule> rules;
	private List<Predicate> predicates;

	public Configuration() {
		this.rules = new ArrayList<Rule>();
		this.predicates = new ArrayList<Predicate>();
	}
	
	public List<Rule> getRules() {
		return new ArrayList<Rule>(this.rules);
	}

	public void setRules(List<Rule> rules) {
		this.rules = new ArrayList<Rule>(rules);
	}

	public List<Predicate> getPredicates() {
		return new ArrayList<Predicate>(this.predicates);
	}

	public void setPredicates(List<Predicate> predicates) {
		this.predicates = new ArrayList<Predicate>(predicates);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configuration other = (Configuration) obj;
		if (predicates == null) {
			if (other.predicates != null)
				return false;
		} else if (!predicates.equals(other.predicates))
			return false;
		if (rules == null) {
			if (other.rules != null)
				return false;
		} else if (!rules.equals(other.rules))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((predicates == null) ? 0 : predicates.hashCode());
		result = prime * result + ((rules == null) ? 0 : rules.hashCode());
		return result;
	}
	
}

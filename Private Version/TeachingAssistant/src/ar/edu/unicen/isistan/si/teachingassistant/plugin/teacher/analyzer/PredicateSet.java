package ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.analyzer;

import java.util.ArrayList;

public class PredicateSet {

	private ArrayList<PrologPredicate> predicates;

	public PredicateSet() {
		this.predicates = new ArrayList<PrologPredicate>();
	}

	public ArrayList<PrologPredicate> getPredicates() {
		return predicates;
	}

	public void setPredicates(ArrayList<PrologPredicate> predicates) {
		this.predicates = predicates;
	}

	public void addPredicate(PrologPredicate predicate) {
		this.predicates.add(predicate);
	}

	public void removePredicate(PrologPredicate predicate) {
		this.predicates.remove(predicate);
	}

}

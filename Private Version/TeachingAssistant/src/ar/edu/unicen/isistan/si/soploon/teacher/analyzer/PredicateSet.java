package ar.edu.unicen.isistan.si.soploon.teacher.analyzer;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("predicates-set")
public class PredicateSet {

	@XStreamAlias("predicates")
	private ArrayList<Predicate> predicates;

	public PredicateSet() {
		this.predicates = new ArrayList<Predicate>();
	}

	public ArrayList<Predicate> getPredicates() {
		return predicates;
	}

	public void setPredicates(ArrayList<Predicate> predicates) {
		this.predicates = predicates;
	}

	public void addPredicate(Predicate predicate) {
		this.predicates.add(predicate);
	}

	public void removePredicate(Predicate predicate) {
		this.predicates.remove(predicate);
	}

}

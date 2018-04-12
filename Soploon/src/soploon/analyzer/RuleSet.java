package soploon.analyzer;

import java.util.Vector;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("rules-set")
public class RuleSet {

	@XStreamAlias("rules")
	private Vector<Rule> rules;

	public RuleSet() {
		
	}
	
	public Vector<Rule> getRules() {
		return rules;
	}

	public void setRules(Vector<Rule> rules) {
		this.rules = rules;
	}
	
	public void addRule(Rule rule) {
		this.rules.add(rule);
	}

}

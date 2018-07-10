package soploon.analyzer;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("rules-set")
public class RuleSet {

	@XStreamAlias("rules")
	private ArrayList<Rule> rules;

	public RuleSet() {
		this.rules = new ArrayList<Rule>();
	}
	
	public ArrayList<Rule> getRules() {
		return rules;
	}

	public void setRules(ArrayList<Rule> rules) {
		this.rules = rules;
	}
	
	public void addRule(Rule rule) {
		this.rules.add(rule);
	}

}

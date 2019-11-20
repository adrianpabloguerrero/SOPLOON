package ar.edu.unicen.isistan.si.soploon.teacher.analyzer;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("rules-set")
public class RuleSet {

	@XStreamAlias("rules")
	private ArrayList<Rule> rules;
	@XStreamAlias("version")
	private String version;
	
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
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String toString() {
		return "RuleSet: " + this.version;
}

}

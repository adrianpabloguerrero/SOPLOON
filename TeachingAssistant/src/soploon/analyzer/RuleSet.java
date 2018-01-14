package soploon.analyzer;

import java.util.Vector;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("rules-set")
public class RuleSet {

	@XStreamAlias("rules")
	private Vector<Rule> rules;
	@XStreamAlias("version")
	private String version;

	public RuleSet() {
		
	}
	
	public Vector<Rule> getRules() {
		return rules;
	}

	public void setRules(Vector<Rule> rules) {
		this.rules = rules;
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

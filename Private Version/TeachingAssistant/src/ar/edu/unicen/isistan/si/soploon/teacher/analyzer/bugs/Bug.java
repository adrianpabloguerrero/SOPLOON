
package ar.edu.unicen.isistan.si.soploon.teacher.analyzer.bugs;

import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.dom.ASTNode;

import ar.edu.unicen.isistan.si.soploon.teacher.analyzer.Rule;
import ar.edu.unicen.isistan.si.soploon.teacher.modeler.converters.NodeConverterFactory;

public class Bug implements Comparable<Bug> {

	private Rule rule;
	private List<BuggedCode> codes;
	
	public Bug(Rule rule, Vector<ASTNode> nodes, NodeConverterFactory converter_factory) {
		this.rule = rule;
		this.codes = new Vector<BuggedCode>();
		for (ASTNode node: nodes)
			this.codes.add(new BuggedCode(this,node,converter_factory));
	}
	
	public String getLineNumber() {
		Vector<String> aux = new Vector<String>();
		for (BuggedCode code: this.codes)
			aux.add(code.getLineNumber());
		String out = aux.toString();
		if (aux.size() == 1)
			out = out.substring(1,out.length()-1);
		return out;
	}

	public String getFile() {
		Vector<String> aux = new Vector<String>();
		for (BuggedCode code: this.codes)
			aux.add(code.getFile());
		String out = aux.toString();
		if (aux.size() == 1)
			out = out.substring(1,out.length()-1);
		return out;
	}

	public String getCode() {
		Vector<String> aux = new Vector<String>();
		for (BuggedCode code: this.codes)
			aux.add(code.getCode());
		String out = aux.toString();
		if (aux.size() == 1)
			out = out.substring(1,out.length()-1);
		return out;
	}
	
	public boolean isSimple() {
		return (this.codes.size() == 1);
	}
	
	public String getType() {
		return this.rule.getType();
	}

	public String getDescription() {
		return this.rule.getDescription();
	}

	public IPath getPath() {
		if (this.isSimple())
			return this.codes.get(0).getPath();
		return null;
	}
	
	public List<BuggedCode> getBuggedCodes() {
		return this.codes;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public String toString() {
		return this.getType() + ", " + this.getFile() + ", " + this.getLineNumber() + ", " + this.getCode() + ", " + this.getDescription();
	}

	@Override
	public int compareTo(Bug bug) {
		return this.getType().compareTo(bug.getType());
	}
	
	public void open() {
		if (this.isSimple())
			this.codes.get(0).open();
	}

	
}

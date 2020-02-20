
package ar.edu.unicen.isistan.si.soploon.plugin.teacher.analyzer.bugs;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.IPath;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.analyzer.PrologRule;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.converters.NodeConverterFactory;
import ar.edu.unicen.isistan.si.soploon.server.models.CodeLocation;
import ar.edu.unicen.isistan.si.soploon.server.models.Error;

public class Bug implements Comparable<Bug> {

	private PrologRule rule;
	private List<BuggedCode> codes;

	public Bug(PrologRule rule, ArrayList<Integer> nodeIds, Mapper mapper, NodeConverterFactory converterFactory) {
		this.rule = rule;
		this.codes = new Vector<BuggedCode>();
		for (Integer nodeId: nodeIds)
			this.codes.add(new BuggedCode(this,nodeId,mapper,converterFactory));
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
		return this.rule.getName();
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

	public PrologRule getRule() {
		return rule;
	}

	public void setRule(PrologRule rule) {
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

	public Error toError() {
		Error error = new Error();
		
		ArrayList<CodeLocation> codeLocations = new ArrayList<CodeLocation>();
		ArrayList<Integer> representation = new ArrayList<Integer>();
		
		for (BuggedCode buggedCode: this.codes) {
			CodeLocation codeLocation = buggedCode.toCodeLocation();
			codeLocations.add(codeLocation);
			representation.add(buggedCode.getNodeID());
		}
		
		error.setReviewed(0);
		error.setCodeLocation(codeLocations);
		error.setRuleId(this.rule.getId());
		error.setVersionRule(this.rule.getVersion());
		error.setRepresentationLocation(representation);
		
		return error;
	}	
}

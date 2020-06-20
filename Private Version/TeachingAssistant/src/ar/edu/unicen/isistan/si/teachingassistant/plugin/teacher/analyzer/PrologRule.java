
package ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.analyzer;

import java.util.ArrayList;
import java.util.List;

import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.NoMoreSolutionException;
import alice.tuprolog.NoSolutionException;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.UnknownVarException;
import alice.tuprolog.Var;
import ar.edu.unicen.isistan.si.soploon.server.models.Rule;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.analyzer.bugs.Bug;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.converters.NodeConverterFactory;

public class PrologRule implements Comparable<PrologRule> {

	private Rule rule;

	public PrologRule(Rule rule) {
		this.rule = rule;
	}

	public List<Bug> execute(Prolog engine, Mapper mapper, NodeConverterFactory converterFactory) {
		List<Bug> returnList = new ArrayList<Bug>();

		try {
			String query = this.rule.getQuery() + "(ID).";
			SolveInfo info = engine.solve(query);

			while (info.isSuccess()) {
				Term term = info.getTerm("ID");
				ArrayList<Integer> nodesIds = this.plain(term);
				Bug bug = new Bug(this, nodesIds, mapper, converterFactory);
				returnList.add(bug);

				if (engine.hasOpenAlternatives()) {
					info = engine.solveNext();
				} else {
					break;
				}
			}
		} catch (MalformedGoalException | NoMoreSolutionException | NoSolutionException | UnknownVarException e) {
			e.printStackTrace();
		}
		return returnList;
	}

	@SuppressWarnings("deprecation")
	private ArrayList<Integer> plain(Term term) {
		ArrayList<Integer> out = new ArrayList<Integer>();
		if (!term.isList()) {
			out.add(Integer.valueOf(term.toString()));
		} else if (term.isStruct()) {
			Struct struct = (Struct) term;
			int arity = struct.getArity();
			for (int i = 0; i < arity; i++)
				out.addAll(plain(struct.getArg(i)));
		} else if (term.isVar()) {
			Var var = (Var) term;
			out.addAll(plain(var.getTerm()));
		}
		return out;
	}

	@Override
	public int compareTo(PrologRule rule) {
		return this.getName().compareTo(rule.getName());
	}

	public String getPredicates() {
		return this.rule.getCode();
	}

	public boolean isActivated() {
		return this.rule.getActivated();
	}

	public String getName() {
		return this.rule.getName();
	}

	public String getDescription() {
		return this.rule.getDescription();
	}

	public String getLink() {
		return this.rule.getLink();
	}
	
	public int getId() {
		return this.rule.getId();
	}
	
	public int getVersion() {
		return this.rule.getVersion();
	}
	
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o == this)
			return true;
		return false;
	}
	
}

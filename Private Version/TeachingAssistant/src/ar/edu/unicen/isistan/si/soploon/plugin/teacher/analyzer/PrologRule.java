
package ar.edu.unicen.isistan.si.soploon.plugin.teacher.analyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;

import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.NoMoreSolutionException;
import alice.tuprolog.NoSolutionException;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.UnknownVarException;
import alice.tuprolog.Var;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.analyzer.bugs.Bug;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.converters.NodeConverterFactory;
import ar.edu.unicen.isistan.si.soploon.server.models.Rule;

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
				Vector<String> nodes_ids = plain(term);
				Vector<ASTNode> nodes = getNodes(nodes_ids, mapper);
				Bug bug = new Bug(this, nodes, converterFactory);
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
	private Vector<String> plain(Term term) {
		Vector<String> out = new Vector<String>();
		if (!term.isList()) {
			out.add(term.toString());
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

	private Vector<ASTNode> getNodes(Vector<String> nodesIDs, Mapper mapper) {
		Vector<ASTNode> nodes = new Vector<ASTNode>();
		for (String id : nodesIDs)
			nodes.add(mapper.getNode(id));
		return nodes;
	}

	@Override
	public int compareTo(PrologRule rule) {
		return this.getName().compareTo(rule.getName());
	}

	public String getPredicates() {
		return this.rule.getCode();
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o == this)
			return true;
		return false;
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
	
}

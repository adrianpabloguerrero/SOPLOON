
package soploon.analyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.NoMoreSolutionException;
import alice.tuprolog.NoSolutionException;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.UnknownVarException;
import alice.tuprolog.Var;
import soploon.analyzer.bugs.Bug;
import soploon.modeler.Mapper;
import soploon.modeler.converters.NodeConverterFactory;

@XStreamAlias("simple-rule")
public class Rule implements Comparable<Rule>{
    
	private String type;
    private String description;
    private String query;
    
    public Rule() {
        this.type = this.getClass().getName();
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public String getQuery() {
		return this.query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<Bug> execute(Prolog engine, Mapper mapper, NodeConverterFactory converter_factory) {
		List<Bug> returnList = new ArrayList<Bug>();

		try {
			String query = this.getQuery() + "(ID).";
			SolveInfo info = engine.solve(query);

			while (info.isSuccess()) {
				Term term = info.getTerm("ID");
				Vector<String> nodes_ids = plain(term);
				Vector<ASTNode> nodes = getNodes(nodes_ids, mapper);
				Bug bug = new Bug(this,nodes,converter_factory);
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
		}
		else if (term.isStruct()){
			Struct struct = (Struct) term;
			int arity = struct.getArity();
			for (int i = 0; i < arity; i++)
				out.addAll(plain(struct.getArg(i)));
		}
		else if (term.isVar()) {
			Var var = (Var) term;
			out.addAll(plain(var.getTerm()));
		}
		return out;
	}	

	private Vector<ASTNode> getNodes(Vector<String> nodes_ids, Mapper mapper) {
		Vector<ASTNode> nodes = new Vector<ASTNode>();
		for (String id : nodes_ids)
			nodes.add(mapper.getNode(id));
		return nodes;
	}

	@Override
	public int compareTo(Rule rule) {
		return this.getType().compareTo(rule.getType());
	}
}


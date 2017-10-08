package teachingassistant.modeler;

import java.text.Normalizer;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

public abstract class Visitor extends ASTVisitor {

	protected Mapper mapper;
	protected PrologCode code;
	
	protected Visitor(Mapper mapper, PrologCode code) {
		this.mapper = mapper;
		this.code = code;
	}
	
	protected String generateList(List<ASTNode> nodes) {
		String out = "[";
		if (nodes != null && !nodes.isEmpty()) {
			for (ASTNode node : nodes)
				out += this.mapper.getNodeID(node) + ",";
			out = out.substring(0, out.length() - 1);
		}
		out += "]";
		return out;
	}
	
	
	protected String quote(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\x00-\\x7F]", "");
		return "'" + str.replace("'", "") + "'";
	}
	
	protected String operator(String operator) {
		switch (operator) {
		case "=":
			return "ASSIGN";
		case "+=":
			return "PLUS_ASSIGN";
		case "-=":
			return "MINUS_ASSIGN";
		case "*=":
			return "TIMES_ASSIGN";
		case "/=":
			return "DIVIDE_ASSIGN";
		case "&=":
			return "BIT_AND_ASSIGN";
		case "|=":
			return "BIT_OR_ASSIGN";
		case "^=":
			return "BIT_XOR_ASSIGN";
		case "%=":
			return "REMAINDER_ASSIGN";
		case "<<=":
			return "LEFT_SHIFT_ASSIGN";
		case ">>=":
			return "RIGHT_SHIFT_SIGNED_ASSIGN";
		case ">>>=":
			return "RIGHT_SHIFT_UNSIGNED_ASSIGN";
		case "*":
			return "TIMES";
		case "/":
			return "DIVIDE";
		case "%":
			return "REMAINDER";
		case "+":
			return "PLUS";
		case "-":
			return "MINUS";
		case "<<":
			return "LEFT_SHIFT";
		case ">>":
			return "RIGHT_SHIFT_SIGNED";
		case ">>>":
			return "RIGHT_SHIFT_UNSIGNED";
		case "<":
			return "LESS";
		case ">":
			return "GREATER";
		case "<=":
			return "LESS_EQUALS";
		case ">=":
			return "GREATER_EQUALS";
		case "==":
			return "EQUALS";
		case "!=":
			return "NOT_EQUALS";
		case "^":
			return "XOR";
		case "&":
			return "AND";
		case "|":
			return "OR";
		case "&&":
			return "CONDITIONAL_AND";
		case "||":
			return "CONDITIONAL_OR";
		case "++":
			return "INCREMENT";
		case "--":
			return "DECREMENT";
		case "~":
			return "COMPLEMENT";
		case "!":
			return "NOT";
		}
		return null;
	}
	
	
	protected String[] generateArgs(String[] keys, String[] values) {
		String[] out = new String[keys.length];
		for (int i = 0; i < out.length; i++) {
			if (keys[i] != null)
				out[i] = keys[i] + "(" + values[i] + ")";
			else
				out[i] = values[i];
		}
		return out;
	}
}

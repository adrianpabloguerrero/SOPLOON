
package ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ExpressionMethodReference;

import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.PrologCode;

public class ExpressionMethodReferencePrologConverter extends NodeConverter<ExpressionMethodReference> {

	private static final String KEY = "expression_method_reference";
	private static final String[] KEYS = new String[] { null, "parent", "expression", "method", "arguments",
			"body_declaration", "type_declaration", "compilation_unit" };

	public ExpressionMethodReferencePrologConverter(Mapper mapper, PrologCode code,
			NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(ExpressionMethodReference node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String method = this.mapper.getBindingID(node.resolveMethodBinding());

		this.converter_factory.getConverter(node.getExpression()).convert(node.getExpression());
		String expression_id = this.mapper.getNodeID(node.getExpression());

		Vector<ASTNode> arguments_nodes = new Vector<ASTNode>();
		arguments_nodes.addAll(node.typeArguments());
		for (ASTNode argument : arguments_nodes)
			this.converter_factory.getConverter(argument).convert(argument);
		String arguments = this.generateList(arguments_nodes);

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, expression_id, method, arguments, body_declaration,
				type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(ExpressionMethodReference node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getExpression()).bind(node.getExpression());

		Vector<ASTNode> arguments_nodes = new Vector<ASTNode>();
		arguments_nodes.addAll(node.typeArguments());
		for (ASTNode argument : arguments_nodes)
			this.converter_factory.getConverter(argument).bind(argument);
	}

}

package soploon.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodInvocation;

import soploon.modeler.Mapper;
import soploon.modeler.PrologCode;

public class MethodInvocationPrologConverter extends NodeConverter<MethodInvocation> {

	private static final String KEY = "method_invocation";
	private static final String[] KEYS = new String[] { null, "parent", "expression", "method", "arguments",
			"type_arguments", "body_declaration", "type_declaration", "compilation_unit" };

	public MethodInvocationPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(MethodInvocation node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String expression = null;
		if (node.getExpression() != null) {
			this.converter_factory.getConverter(node.getExpression()).convert(node.getExpression());
			expression = this.mapper.getNodeID(node.getExpression());
		}

		this.converter_factory.getConverter(node.getName()).convert(node.getName());
		String method = this.mapper.getNodeID(node.getName());

		Vector<ASTNode> arguments_nodes = new Vector<ASTNode>();
		arguments_nodes.addAll(node.arguments());
		for (ASTNode argument : arguments_nodes)
			this.converter_factory.getConverter(argument).convert(argument);
		String arguments = this.generateList(arguments_nodes);

		Vector<ASTNode> arguments_types_nodes = new Vector<ASTNode>();
		arguments_types_nodes.addAll(node.typeArguments());
		for (ASTNode type_argument_node : arguments_types_nodes)
			this.converter_factory.getConverter(type_argument_node).convert(type_argument_node);
		String type_arguments = this.generateList(arguments_types_nodes);

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, expression, method, arguments, type_arguments, body_declaration,
				type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(MethodInvocation node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		Vector<ASTNode> arguments_nodes = new Vector<ASTNode>();
		arguments_nodes.addAll(node.arguments());
		for (ASTNode argument : arguments_nodes)
			this.converter_factory.getConverter(argument).bind(argument);

		Vector<ASTNode> arguments_types_nodes = new Vector<ASTNode>();
		arguments_types_nodes.addAll(node.typeArguments());
		for (ASTNode type_argument_node : arguments_types_nodes)
			this.converter_factory.getConverter(type_argument_node).bind(type_argument_node);

		this.converter_factory.getConverter(node.getExpression()).bind(node.getExpression());

		this.converter_factory.getConverter(node.getName()).bind(node.getName());
	}

}

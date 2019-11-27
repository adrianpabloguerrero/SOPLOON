package ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;

import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.PrologCode;

public class SuperMethodInvocationPrologConverter extends NodeConverter<SuperMethodInvocation> {

	private static final String KEY = "super_method_invocation";
	private static final String[] KEYS = new String[] { null, "parent", "method", "arguments", "body_declaration", "type_declaration", "compilation_unit" };

	public SuperMethodInvocationPrologConverter(Mapper mapper, PrologCode code,
			NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(SuperMethodInvocation node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getName()).convert(node.getName());
		String name = this.mapper.getNodeID(node.getName());

		Vector<ASTNode> arguments_nodes = new Vector<ASTNode>();
		arguments_nodes.addAll(node.arguments());
		for (ASTNode argument : arguments_nodes)
			this.converter_factory.getConverter(argument).convert(argument);
		String arguments = this.generateList(arguments_nodes);

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, name, arguments, body_declaration, type_declaration,
				unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(SuperMethodInvocation node) {
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

		this.converter_factory.getConverter(node.getName()).bind(node.getName());
	}

}

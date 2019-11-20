package ar.edu.unicen.isistan.si.soploon.teacher.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ArrayCreation;

import ar.edu.unicen.isistan.si.soploon.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.teacher.modeler.PrologCode;

public class ArrayCreationPrologConverter extends NodeConverter<ArrayCreation> {

	private static final String KEY = "array_creation";
	private static final String[] KEYS = new String[] { null, "parent", "type", "dimensions", "initializer",
			"body_declaration", "type_declaration", "compilation_unit" };

	protected ArrayCreationPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(ArrayCreation node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getType()).convert(node.getType());
		String type = this.mapper.getNodeID(node.getType());

		Vector<ASTNode> expressions_nodes = new Vector<ASTNode>();
		expressions_nodes.addAll(node.dimensions());
		for (ASTNode expression : expressions_nodes)
			this.converter_factory.getConverter(expression).convert(expression);
		String expressions = this.generateList(expressions_nodes);

		String initializer = null;
		if (node.getInitializer() != null) {
			this.converter_factory.getConverter(node.getInitializer()).convert(node.getInitializer());
			initializer = this.mapper.getNodeID(node.getInitializer());
		}

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, type, expressions, initializer, body_declaration, type_declaration,
				unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(ArrayCreation node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getType()).bind(node.getType());

		Vector<ASTNode> expressions_nodes = new Vector<ASTNode>();
		expressions_nodes.addAll(node.dimensions());
		for (ASTNode expression : expressions_nodes)
			this.converter_factory.getConverter(expression).bind(expression);

		if (node.getInitializer() != null)
			this.converter_factory.getConverter(node.getInitializer()).bind(node.getInitializer());

	}

}

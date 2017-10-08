package teachingassistant.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ArrayInitializer;

import teachingassistant.modeler.Mapper;
import teachingassistant.modeler.PrologCode;

public class ArrayInitializerPrologConverter extends NodeConverter<ArrayInitializer> {

	private static final String KEY = "array_initializer";
	private static final String[] KEYS = new String[] { null, "parent", "expressions", "body_declaration",
			"type_declaration", "compilation_unit" };

	public ArrayInitializerPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(ArrayInitializer node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		Vector<ASTNode> expressions_nodes = new Vector<ASTNode>();
		expressions_nodes.addAll(node.expressions());
		for (ASTNode expression : expressions_nodes)
			this.converter_factory.getConverter(expression).convert(expression);
		String expressions = this.generateList(expressions_nodes);

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, expressions, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(ArrayInitializer node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		Vector<ASTNode> expressions_nodes = new Vector<ASTNode>();
		expressions_nodes.addAll(node.expressions());
		for (ASTNode expression : expressions_nodes)
			this.converter_factory.getConverter(expression).bind(expression);

	}

}

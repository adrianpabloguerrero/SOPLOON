
package assistant.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ForStatement;

import assistant.modeler.Mapper;
import assistant.modeler.PrologCode;

public class ForStatementPrologConverter extends NodeConverter<ForStatement> {

	private static final String KEY = "for_statement";
	private static final String[] KEYS = new String[] { null, "parent", "initializers", "expression", "updaters",
			"body", "body_declaration", "type_declaration", "compilation_unit" };

	public ForStatementPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(ForStatement node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		Vector<ASTNode> initializers_nodes = new Vector<ASTNode>();
		initializers_nodes.addAll(node.initializers());
		for (ASTNode initializer : initializers_nodes)
			this.converter_factory.getConverter(initializer).convert(initializer);
		String initializers = this.generateList(initializers_nodes);

		this.converter_factory.getConverter(node.getExpression()).convert(node.getExpression());
		String expression = this.mapper.getNodeID(node.getExpression());

		Vector<ASTNode> updaters_nodes = new Vector<ASTNode>();
		updaters_nodes.addAll(node.updaters());
		for (ASTNode updaters : updaters_nodes)
			this.converter_factory.getConverter(updaters).convert(updaters);
		String updaters = this.generateList(updaters_nodes);

		this.converter_factory.getConverter(node.getBody()).convert(node.getBody());
		String body = this.mapper.getNodeID(node.getBody());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, initializers, expression, updaters, body, body_declaration,
				type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(ForStatement node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		Vector<ASTNode> initializers_nodes = new Vector<ASTNode>();
		initializers_nodes.addAll(node.initializers());
		for (ASTNode initializer : initializers_nodes)
			this.converter_factory.getConverter(initializer).bind(initializer);

		this.converter_factory.getConverter(node.getExpression()).bind(node.getExpression());

		Vector<ASTNode> updaters_nodes = new Vector<ASTNode>();
		updaters_nodes.addAll(node.updaters());
		for (ASTNode updaters : updaters_nodes)
			this.converter_factory.getConverter(updaters).bind(updaters);

		this.converter_factory.getConverter(node.getBody()).bind(node.getBody());

	}

}

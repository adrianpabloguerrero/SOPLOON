package soploon.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;

import soploon.modeler.Mapper;
import soploon.modeler.PrologCode;

public class BlockPrologConverter extends NodeConverter<Block> {

	private static final String KEY = "block";
	private static final String[] KEYS = new String[] { null, "parent", "statements", "body_declaration",
			"type_declaration", "compilation_unit" };

	public BlockPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(Block node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		Vector<ASTNode> statements_nodes = new Vector<ASTNode>();
		statements_nodes.addAll(node.statements());
		for (ASTNode statement : statements_nodes)
			this.converter_factory.getConverter(statement).convert(statement);
		String statements = this.generateList(statements_nodes);

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, statements, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(Block node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		Vector<ASTNode> statements_nodes = new Vector<ASTNode>();
		statements_nodes.addAll(node.statements());
		for (ASTNode statement : statements_nodes)
			this.converter_factory.getConverter(statement).bind(statement);

	}

}

package ar.edu.unicen.isistan.si.soploon.teacher.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.SwitchStatement;

import ar.edu.unicen.isistan.si.soploon.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.teacher.modeler.PrologCode;

public class SwitchStatementPrologConverter extends NodeConverter<SwitchStatement> {

	private static final String KEY = "switch_statement";
	private static final String[] KEYS = new String[] { null, "parent", "switch", "statements", "body_declaration",
			"type_declaration", "compilation_unit" };

	public SwitchStatementPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(SwitchStatement node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getExpression()).convert(node.getExpression());
		String expression = this.mapper.getNodeID(node.getExpression());

		Vector<ASTNode> statements_nodes = new Vector<ASTNode>();
		statements_nodes.addAll(node.statements());
		for (ASTNode statement : statements_nodes)
			this.converter_factory.getConverter(statement).convert(statement);
		String statements = this.generateList(statements_nodes);

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, expression, statements, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(SwitchStatement node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getExpression()).bind(node.getExpression());

		Vector<ASTNode> statements_nodes = new Vector<ASTNode>();
		statements_nodes.addAll(node.statements());
		for (ASTNode statement : statements_nodes)
			this.converter_factory.getConverter(statement).bind(statement);
	}
	
	public String getName(SwitchStatement node) {
		return "switch(" + node.getExpression().toString() + ")";
	}


}

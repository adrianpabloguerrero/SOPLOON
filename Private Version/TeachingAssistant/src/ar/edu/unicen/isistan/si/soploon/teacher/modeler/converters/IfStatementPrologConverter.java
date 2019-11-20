
package ar.edu.unicen.isistan.si.soploon.teacher.modeler.converters;

import org.eclipse.jdt.core.dom.IfStatement;

import ar.edu.unicen.isistan.si.soploon.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.teacher.modeler.PrologCode;

public class IfStatementPrologConverter extends NodeConverter<IfStatement> {

	private static final String KEY = "if_statement";
	private static final String[] KEYS = new String[] { null, "parent", "condition", "then", "else", "body_declaration",
			"type_declaration", "compilation_unit" };

	public IfStatementPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(IfStatement node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getExpression()).convert(node.getExpression());
		String condition = this.mapper.getNodeID(node.getExpression());

		this.converter_factory.getConverter(node.getThenStatement()).convert(node.getThenStatement());
		String then_block = this.mapper.getNodeID(node.getThenStatement());

		this.converter_factory.getConverter(node.getElseStatement()).convert(node.getElseStatement());
		String else_block = this.mapper.getNodeID(node.getElseStatement());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, condition, then_block, else_block, body_declaration,
				type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(IfStatement node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getExpression()).bind(node.getExpression());

		this.converter_factory.getConverter(node.getThenStatement()).bind(node.getThenStatement());

		this.converter_factory.getConverter(node.getElseStatement()).bind(node.getElseStatement());
	}

	public String getName(IfStatement node) {
		return "if(" + node.getExpression().toString() + ")";
	}
}

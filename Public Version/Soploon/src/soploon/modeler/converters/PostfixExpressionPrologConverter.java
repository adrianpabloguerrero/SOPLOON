
package soploon.modeler.converters;

import org.eclipse.jdt.core.dom.PostfixExpression;

import soploon.modeler.Mapper;
import soploon.modeler.PrologCode;

public class PostfixExpressionPrologConverter extends NodeConverter<PostfixExpression> {

	private static final String KEY = "postfix_expression";
	private static final String[] KEYS = new String[] { null, "parent", "operator", "operand", "body_declaration",
			"type_declaration", "compilation_unit" };

	public PostfixExpressionPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(PostfixExpression node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String operator = this.quote(this.operator(node.getOperator().toString()));

		this.converter_factory.getConverter(node.getOperand()).convert(node.getOperand());
		String operand = this.mapper.getNodeID(node.getOperand());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, operator, operand, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(PostfixExpression node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getOperand()).bind(node.getOperand());
	}
}

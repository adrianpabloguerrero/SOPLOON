
package soploon.modeler.converters;

import org.eclipse.jdt.core.dom.ConditionalExpression;

import soploon.modeler.Mapper;
import soploon.modeler.PrologCode;


public class ConditionalExpressionPrologConverter extends NodeConverter<ConditionalExpression> {

	private static final String KEY = "conditional_expression";
	private static final String[] KEYS = new String[] { null, "parent", "condition", "then", "else", "body_declaration",
			"type_declaration", "compilation_unit" };

	public ConditionalExpressionPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(ConditionalExpression node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getExpression()).convert(node.getExpression());
		String expression = this.mapper.getNodeID(node.getExpression());

		this.converter_factory.getConverter(node.getThenExpression())
				.convert(node.getThenExpression());
		String then_expression = this.mapper.getNodeID(node.getThenExpression());

		this.converter_factory.getConverter(node.getElseExpression())
				.convert(node.getElseExpression());
		String else_expression = this.mapper.getNodeID(node.getElseExpression());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());
	
		String[] args = new String[] { id, parent, expression, then_expression, else_expression,
				body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(ConditionalExpression node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getExpression()).bind(node.getExpression());

		this.converter_factory.getConverter(node.getThenExpression()).bind(node.getThenExpression());
		
		this.converter_factory.getConverter(node.getElseExpression()).bind(node.getElseExpression());

	}
}

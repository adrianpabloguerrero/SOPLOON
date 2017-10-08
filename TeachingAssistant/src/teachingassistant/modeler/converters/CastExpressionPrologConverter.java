package teachingassistant.modeler.converters;

import org.eclipse.jdt.core.dom.CastExpression;

import teachingassistant.modeler.Mapper;
import teachingassistant.modeler.PrologCode;

public class CastExpressionPrologConverter extends NodeConverter<CastExpression> {

	private static final String KEY = "cast_expression";
	private static final String[] KEYS = new String[] { null, "parent", "type", "expression", "body_declaration",
			"type_declaration", "compilation_unit" };

	public CastExpressionPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(CastExpression node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getType()).convert(node.getType());
		String type = this.mapper.getNodeID(node.getType());

		this.converter_factory.getConverter(node.getExpression()).convert(node.getExpression());
		String expression = this.mapper.getNodeID(node.getExpression());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, type, expression, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(CastExpression node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getType()).bind(node.getType());

		this.converter_factory.getConverter(node.getExpression()).bind(node.getExpression());
	}
}

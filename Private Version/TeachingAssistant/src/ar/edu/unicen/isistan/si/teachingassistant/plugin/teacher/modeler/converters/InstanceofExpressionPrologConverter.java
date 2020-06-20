
package ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.converters;

import org.eclipse.jdt.core.dom.InstanceofExpression;

import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.PrologCode;

public class InstanceofExpressionPrologConverter extends NodeConverter<InstanceofExpression> {

	private static final String KEY = "instanceof_expression";
	private static final String[] KEYS = new String[] { null, "parent", "expression", "type", "body_declaration",
			"type_declaration", "compilation_unit" };

	public InstanceofExpressionPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(InstanceofExpression node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getLeftOperand()).convert(node.getLeftOperand());
		String expression = this.mapper.getNodeID(node.getLeftOperand());

		this.converter_factory.getConverter(node.getRightOperand()).convert(node.getRightOperand());
		String type = this.mapper.getNodeID(node.getRightOperand());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, expression, type, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(InstanceofExpression node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getLeftOperand()).bind(node.getLeftOperand());

		this.converter_factory.getConverter(node.getRightOperand()).bind(node.getRightOperand());

	}
}


package assistant.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.InfixExpression;

import assistant.modeler.Mapper;
import assistant.modeler.PrologCode;

public class InfixExpressionPrologConverter extends NodeConverter<InfixExpression> {

	private static final String KEY = "infix_expression";
	private static final String[] KEYS = new String[] { null, "parent", "operator", "left_operand", "right_operand",
			"extended_operands", "body_declaration", "type_declaration", "compilation_unit" };

	public InfixExpressionPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(InfixExpression node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String operator = this.quote(this.operator(node.getOperator().toString()));

		this.converter_factory.getConverter(node.getLeftOperand()).convert(node.getLeftOperand());
		String left_operand = this.mapper.getNodeID(node.getLeftOperand());

		this.converter_factory.getConverter(node.getRightOperand()).convert(node.getRightOperand());
		String right_operand = this.mapper.getNodeID(node.getRightOperand());

		Vector<ASTNode> extended_operand_nodes = new Vector<ASTNode>();
		extended_operand_nodes.addAll(node.extendedOperands());
		for (ASTNode extended_operand : extended_operand_nodes)
			this.converter_factory.getConverter(extended_operand).convert(extended_operand);
		String extended_operands = this.generateList(extended_operand_nodes);

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, operator, left_operand, right_operand, extended_operands,
				body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));

	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(InfixExpression node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getLeftOperand()).bind(node.getLeftOperand());

		this.converter_factory.getConverter(node.getRightOperand()).bind(node.getRightOperand());

		Vector<ASTNode> extended_operand_nodes = new Vector<ASTNode>();
		extended_operand_nodes.addAll(node.extendedOperands());
		for (ASTNode extended_operand : extended_operand_nodes)
			this.converter_factory.getConverter(extended_operand).bind(extended_operand);
	}
}

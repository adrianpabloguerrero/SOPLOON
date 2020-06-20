package ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.converters;

import org.eclipse.jdt.core.dom.Assignment;

import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.PrologCode;

public class AssignmentPrologConverter extends NodeConverter<Assignment> {

	private static final String KEY = "assignment";
	private static final String[] KEYS = new String[] { null, "parent", "operator", "left_operand", "right_operand",
			"body_declaration", "type_declaration", "compilation_unit" };

	public AssignmentPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(Assignment node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String operator = this.quote(this.operator(node.getOperator().toString()));

		this.converter_factory.getConverter(node.getLeftHandSide()).convert(node.getLeftHandSide());
		String left_operand = this.mapper.getNodeID(node.getLeftHandSide());

		this.converter_factory.getConverter(node.getRightHandSide()).convert(node.getRightHandSide());
		String right_operand = this.mapper.getNodeID(node.getRightHandSide());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, operator, left_operand, right_operand, body_declaration,
				type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(Assignment node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getLeftHandSide()).bind(node.getLeftHandSide());

		this.converter_factory.getConverter(node.getRightHandSide()).bind(node.getRightHandSide());
	}
}

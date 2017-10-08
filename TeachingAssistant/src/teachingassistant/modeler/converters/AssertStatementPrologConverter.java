package teachingassistant.modeler.converters;

import org.eclipse.jdt.core.dom.AssertStatement;

import teachingassistant.modeler.Mapper;
import teachingassistant.modeler.PrologCode;

public class AssertStatementPrologConverter extends NodeConverter<AssertStatement> {

	private static final String KEY = "assert_statement";
	private static final String[] KEYS = new String[] { null, "parent", "expression", "message", "body_declaration",
			"type_declaration", "compilation_unit" };

	public AssertStatementPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(AssertStatement node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getExpression()).convert(node.getExpression());
		String expression_id = this.mapper.getNodeID(node.getExpression());

		this.converter_factory.getConverter(node.getMessage()).convert(node.getMessage());
		String message_id = this.mapper.getNodeID(node.getMessage());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, expression_id, message_id, body_declaration, type_declaration,
				unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));

	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(AssertStatement node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getExpression()).bind(node.getExpression());

		this.converter_factory.getConverter(node.getMessage()).bind(node.getMessage());
	}
}

package teachingassistant.modeler.converters;

import org.eclipse.jdt.core.dom.ReturnStatement;

import teachingassistant.modeler.Mapper;
import teachingassistant.modeler.PrologCode;

public class ReturnStatementPrologConverter extends NodeConverter<ReturnStatement> {

	private static final String KEY = "return_statement";
	private static final String[] KEYS = new String[] { null, "parent", "expression", "body_declaration",
			"type_declaration", "compilation_unit" };

	public ReturnStatementPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(ReturnStatement node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getExpression()).convert(node.getExpression());
		String expression = this.mapper.getNodeID(node.getExpression());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, expression, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(ReturnStatement node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getExpression()).bind(node.getExpression());

	}
}

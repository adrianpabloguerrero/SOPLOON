package assistant.modeler.converters;

import org.eclipse.jdt.core.dom.SwitchCase;

import assistant.modeler.Mapper;
import assistant.modeler.PrologCode;

public class SwitchCasePrologConverter extends NodeConverter<SwitchCase> {

	private static final String KEY = "switch_case";
	private static final String[] KEYS = new String[] { null, "parent", "case", "body_declaration", "type_declaration",
			"compilation_unit" };

	public SwitchCasePrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(SwitchCase node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String expression = null;
		if (!node.isDefault()) {
			this.converter_factory.getConverter(node.getExpression()).convert(node.getExpression());
			expression = this.mapper.getNodeID(node.getExpression());
		}

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, expression, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));

	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(SwitchCase node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getExpression()).bind(node.getExpression());
	}
}

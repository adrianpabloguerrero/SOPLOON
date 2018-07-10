package assistant.modeler.converters;

import org.eclipse.jdt.core.dom.CatchClause;

import assistant.modeler.Mapper;
import assistant.modeler.PrologCode;

public class CatchClausePrologConverter extends NodeConverter<CatchClause> {

	private static final String KEY = "catch_clause";
	private static final String[] KEYS = new String[] { null, "parent", "exception", "body", "body_declaration",
			"type_declaration", "compilation_unit" };

	public CatchClausePrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(CatchClause node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getException()).convert(node.getException());
		String exception = this.mapper.getNodeID(node.getException());

		this.converter_factory.getConverter(node.getBody()).convert(node.getBody());
		String block = this.mapper.getNodeID(node.getBody());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, exception, block, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(CatchClause node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getException()).bind(node.getException());

		this.converter_factory.getConverter(node.getBody()).bind(node.getBody());
	}
}

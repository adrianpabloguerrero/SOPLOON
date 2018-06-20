
package assistant.modeler.converters;

import org.eclipse.jdt.core.dom.ContinueStatement;

import assistant.modeler.Mapper;
import assistant.modeler.PrologCode;

public class ContinueStatementPrologConverter extends NodeConverter<ContinueStatement> {

	private static final String KEY = "continue_statement";
	private static final String[] KEYS = new String[] { null, "parent", "label", "body_declaration", "type_declaration",
			"compilation_unit" };

	public ContinueStatementPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@Override
	public void convert(ContinueStatement node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String label = null;
		if (node.getLabel() != null)
			label = this.mapper.getBindingID(node.getLabel().resolveBinding());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, label, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));

	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(ContinueStatement node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		if (node.getLabel() != null)
			this.converter_factory.getConverter(node.getLabel()).bind(node.getLabel());
	}
}

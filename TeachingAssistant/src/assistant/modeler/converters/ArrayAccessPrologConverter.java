package assistant.modeler.converters;

import org.eclipse.jdt.core.dom.ArrayAccess;

import assistant.modeler.Mapper;
import assistant.modeler.PrologCode;

public class ArrayAccessPrologConverter extends NodeConverter<ArrayAccess> {

	private static final String KEY = "array_access";
	private static final String[] KEYS = new String[] { null, "parent", "array", "index", "body_declaration",
			"type_declaration", "compilation_unit" };

	public ArrayAccessPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(ArrayAccess node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getArray()).convert(node.getArray());
		String array_id = this.mapper.getNodeID(node.getArray());

		this.converter_factory.getConverter(node.getIndex()).convert(node.getIndex());
		String index_id = this.mapper.getNodeID(node.getIndex());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent_id, array_id, index_id, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(ArrayAccess node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getArray()).bind(node.getArray());

		this.converter_factory.getConverter(node.getIndex()).bind(node.getIndex());
	}
}

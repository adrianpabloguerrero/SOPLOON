package soploon.modeler.converters;

import org.eclipse.jdt.core.dom.SuperFieldAccess;

import soploon.modeler.Mapper;
import soploon.modeler.PrologCode;

public class SuperFieldAccessPrologConverter extends NodeConverter<SuperFieldAccess> {

	private static final String KEY = "super_field_access";
	private static final String[] KEYS = new String[] { null, "parent", "field", "body_declaration", "type_declaration",
			"compilation_unit" };

	public SuperFieldAccessPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(SuperFieldAccess node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getName()).convert(node.getName());
		String field = this.mapper.getNodeID(node.getName());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, field, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(SuperFieldAccess node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getName()).bind(node.getName());
	}
}

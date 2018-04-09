package soploon.modeler.converters;

import org.eclipse.jdt.core.dom.QualifiedName;

import soploon.modeler.Mapper;
import soploon.modeler.PrologCode;

public class QualifiedNameConverter extends NodeConverter<QualifiedName> {

	private static final String KEY = "qualified_name";
	private static final String[] KEYS = new String[] { null, "parent", "qualified", "name", "body_declaration",
			"type_declaration", "compilation_unit" };

	protected QualifiedNameConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(QualifiedName node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getQualifier()).convert(node.getQualifier());
		String qualifier = this.mapper.getNodeID(node.getQualifier());

		this.converter_factory.getConverter(node.getName()).convert(node.getName());
		String name = this.mapper.getNodeID(node.getName());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, qualifier, name, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(QualifiedName node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getQualifier()).bind(node.getQualifier());

		this.converter_factory.getConverter(node.getName()).bind(node.getName());

	}

}

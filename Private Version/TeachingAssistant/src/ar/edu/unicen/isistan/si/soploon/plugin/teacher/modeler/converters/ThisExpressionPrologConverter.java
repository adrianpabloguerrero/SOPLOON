package ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.converters;

import org.eclipse.jdt.core.dom.ThisExpression;

import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.PrologCode;

public class ThisExpressionPrologConverter extends NodeConverter<ThisExpression> {

	private static final String KEY = "this_expression";
	private static final String[] KEYS = new String[] { null, "parent", "expression", "body_declaration",
			"type_declaration", "compilation_unit" };

	public ThisExpressionPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(ThisExpression node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String qualifier = null;
		if (node.getQualifier() != null) {
			this.converter_factory.getConverter(node.getQualifier()).convert(node.getQualifier());
			qualifier = this.mapper.getNodeID(node.getQualifier());
		}

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, qualifier, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));

	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(ThisExpression node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		if (node.getQualifier() != null)
			this.converter_factory.getConverter(node.getQualifier()).bind(node.getQualifier());
	}
}

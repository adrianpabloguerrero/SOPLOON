package ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.converters;

import org.eclipse.jdt.core.dom.EnhancedForStatement;

import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.PrologCode;

public class EnhancedForStatementPrologConverter extends NodeConverter<EnhancedForStatement> {

	private static final String KEY = "enhanced_for_statement";
	private static final String[] KEYS = new String[] { null, "parent", "parameter", "expression", "body",
			"body_declaration", "type_declaration", "compilation_unit" };

	public EnhancedForStatementPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(EnhancedForStatement node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getParameter()).convert(node.getParameter());
		String parameter = this.mapper.getNodeID(node.getParameter());

		this.converter_factory.getConverter(node.getExpression()).convert(node.getExpression());
		String expression = this.mapper.getNodeID(node.getExpression());

		this.converter_factory.getConverter(node.getBody()).convert(node.getBody());
		String body = this.mapper.getNodeID(node.getBody());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, parameter, expression, body, body_declaration, type_declaration,
				unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(EnhancedForStatement node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getParameter()).bind(node.getParameter());

		this.converter_factory.getConverter(node.getExpression()).bind(node.getExpression());

		this.converter_factory.getConverter(node.getBody()).bind(node.getBody());
	}
}


package ar.edu.unicen.isistan.si.soploon.teacher.modeler.converters;

import org.eclipse.jdt.core.dom.DoStatement;

import ar.edu.unicen.isistan.si.soploon.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.teacher.modeler.PrologCode;

public class DoStatementPrologConverter extends NodeConverter<DoStatement> {

	private static final String KEY = "do_statement";
	private static final String[] KEYS = new String[] { null, "parent", "expression", "body", "body_declaration",
			"type_declaration", "compilation_unit" };

	public DoStatementPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(DoStatement node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getExpression()).convert(node.getExpression());
		String condition = this.mapper.getNodeID(node.getExpression());

		this.converter_factory.getConverter(node.getBody()).convert(node.getBody());
		String block = this.mapper.getNodeID(node.getBody());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, condition, block, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(DoStatement node) {
		this.mapper.getNodeID(node);
		
		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getExpression()).bind(node.getExpression());

		this.converter_factory.getConverter(node.getBody()).bind(node.getBody());

	}
}

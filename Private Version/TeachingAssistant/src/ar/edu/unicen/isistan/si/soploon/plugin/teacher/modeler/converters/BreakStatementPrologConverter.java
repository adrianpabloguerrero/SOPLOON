package ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.converters;

import org.eclipse.jdt.core.dom.BreakStatement;

import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.PrologCode;

public class BreakStatementPrologConverter extends NodeConverter<BreakStatement> {

	private static final String KEY = "break_statement";
	private static final String[] KEYS = new String[] { null, "parent", "label", "body_declaration",
			"type_declaration", "compilation_unit" };

	public BreakStatementPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@Override
	public void convert(BreakStatement node) {
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

	@Override
	public void bind(BreakStatement node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));
	}
}


package ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.converters;

import org.eclipse.jdt.core.dom.NullLiteral;

import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.PrologCode;

public class NullLiteralPrologConverter extends NodeConverter<NullLiteral> {

	private static final String KEY = "null_literal";
	private static final String[] KEYS = new String[] { null, "parent", "body_declaration", "type_declaration",
			"compilation_unit" };

	public NullLiteralPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@Override
	public void convert(NullLiteral node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@Override
	public void bind(NullLiteral node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));
	}
}

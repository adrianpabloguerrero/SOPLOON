
package ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.converters;

import org.eclipse.jdt.core.dom.StringLiteral;

import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.PrologCode;

public class StringLiteralPrologConverter extends NodeConverter<StringLiteral> {

	private static final String KEY = "string_literal";
	private static final String[] KEYS = new String[] { null, "parent", "value", "body_declaration", "type_declaration",
			"compilation_unit" };

	public StringLiteralPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@Override
	public void convert(StringLiteral node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String value = this.quote(node.getEscapedValue());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, value, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@Override
	public void bind(StringLiteral node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));
	}
}

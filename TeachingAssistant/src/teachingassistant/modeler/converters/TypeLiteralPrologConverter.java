package teachingassistant.modeler.converters;

import org.eclipse.jdt.core.dom.TypeLiteral;

import teachingassistant.modeler.Mapper; import teachingassistant.modeler.PrologCode;

public class TypeLiteralPrologConverter extends NodeConverter<TypeLiteral> {

	private static final String KEY = "literal_type";
	private static final String[] KEYS = new String[] { null, "parent",  "type", "body_declaration", "type_declaration", "compilation_unit" };

	public TypeLiteralPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(TypeLiteral node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());
		
		this.converter_factory.getConverter(node.getType()).convert(node.getType());
		String type = this.mapper.getNodeID(node.getType());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, type, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(TypeLiteral node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getType()).bind(node.getType());

	}
}

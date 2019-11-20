package ar.edu.unicen.isistan.si.soploon.teacher.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeParameter;

import ar.edu.unicen.isistan.si.soploon.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.teacher.modeler.PrologCode;

public class TypeParameterPrologConverter extends NodeConverter<TypeParameter> {

	private final static String KEY = "type_parameter";
	private final static String[] KEYS = { null, "parent", null, "modifiers", "bounds", "body_declaration",
			"type_declaration", "compilation_unit" };

	public TypeParameterPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(TypeParameter node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String name = this.quote(node.getName().getIdentifier());

		Vector<IExtendedModifier> modifiers_nodes = new Vector<IExtendedModifier>();
		modifiers_nodes.addAll(node.modifiers());
		Vector<String> modifiers_names = new Vector<String>();
		for (IExtendedModifier modifier : modifiers_nodes) {
			if (modifier.isModifier())
				modifiers_names.add(this.quote(((Modifier) modifier).getKeyword().toString()));
		}
		String modifiers = modifiers_names.toString();

		Vector<ASTNode> types_bounds_nodes = new Vector<ASTNode>();
		types_bounds_nodes.addAll(node.typeBounds());
		for (ASTNode type_bound : types_bounds_nodes)
			this.converter_factory.getConverter(type_bound).convert(type_bound);
		String types_bounds = this.generateList(types_bounds_nodes);

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, name, modifiers, types_bounds, body_declaration, type_declaration,
				unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(TypeParameter node) {
		this.mapper.setBindingID(node.resolveBinding(), this.mapper.getNodeID(node));

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		Vector<ASTNode> types_bounds_nodes = new Vector<ASTNode>();
		types_bounds_nodes.addAll(node.typeBounds());
		for (ASTNode type_bound : types_bounds_nodes)
			this.converter_factory.getConverter(type_bound).bind(type_bound);

	}

}

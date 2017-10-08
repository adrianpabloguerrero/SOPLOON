package teachingassistant.modeler.converters;

import java.util.Arrays;
import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import teachingassistant.modeler.Mapper;
import teachingassistant.modeler.PrologCode;

public class TypeDeclarationPrologConverter extends NodeConverter<TypeDeclaration> {

	private static final String CLASS_KEY = "class_declaration";
	private static final String INTERFACE_KEY = "interface_declaration";
	private static final String[] KEYS = new String[] { null, "parent", null, "parameters_types", "modifiers",
			"super_type", "implements", "fields", "methods", "declarations", "compilation_unit" };

	public TypeDeclarationPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(TypeDeclaration node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String name = this.quote(node.getName().getIdentifier());

		String fact = node.isInterface() ? INTERFACE_KEY : CLASS_KEY;

		Vector<ASTNode> parameters_types_nodes = new Vector<ASTNode>();
		parameters_types_nodes.addAll(node.typeParameters());
		for (ASTNode parameter : parameters_types_nodes)
			this.converter_factory.getConverter(parameter).convert(parameter);
		String parameters_types = this.generateList(parameters_types_nodes);

		String super_type = null;
		if (node.getSuperclassType() != null) {
			this.converter_factory.getConverter(node.getSuperclassType()).convert(node.getSuperclassType());
			super_type = this.mapper.getNodeID(node.getSuperclassType());
		}
		
		Vector<IExtendedModifier> modifiers_nodes = new Vector<IExtendedModifier>();
		modifiers_nodes.addAll(node.modifiers());
		Vector<String> modifiers_names = new Vector<String>();
		for (IExtendedModifier modifier : modifiers_nodes) {
			if (modifier.isModifier())
				modifiers_names.add(this.quote(((Modifier) modifier).getKeyword().toString()));
		}
		String modifiers = modifiers_names.toString();

		Vector<ASTNode> implements_nodes = new Vector<ASTNode>();
		implements_nodes.addAll(node.superInterfaceTypes());
		for (ASTNode implement : implements_nodes)
			this.converter_factory.getConverter(implement).convert(implement);
		String implements_ids = this.generateList(implements_nodes);

		Vector<ASTNode> declarations_nodes = new Vector<ASTNode>();
		declarations_nodes.addAll(node.bodyDeclarations());
		for (ASTNode declaration : declarations_nodes)
			this.converter_factory.getConverter(declaration).convert(declaration);
		String declarations = this.generateList(declarations_nodes);

		Vector<ASTNode> fields_nodes = new Vector<ASTNode>();
		fields_nodes.addAll(Arrays.asList(node.getFields()));
		String fields = this.generateList(fields_nodes);

		Vector<ASTNode> methods_nodes = new Vector<ASTNode>();
		methods_nodes.addAll(Arrays.asList(node.getMethods()));
		String methods = this.generateList(methods_nodes);
		
		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, name, parameters_types, modifiers, super_type, implements_ids,
				fields, methods, declarations, unit };
		this.code.addFact(fact, generateArgs(KEYS, args));

	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(TypeDeclaration node) {
		this.mapper.setBindingID(node.resolveBinding(), this.mapper.getNodeID(node));

		this.mapper.setParent(node,node);

		Vector<ASTNode> parameters_types_nodes = new Vector<ASTNode>();
		parameters_types_nodes.addAll(node.typeParameters());
		for (ASTNode parameter : parameters_types_nodes)
			this.converter_factory.getConverter(parameter).bind(parameter);

		if (node.getSuperclassType() != null)
			this.converter_factory.getConverter(node.getSuperclassType()).bind(node.getSuperclassType());

		Vector<ASTNode> implements_nodes = new Vector<ASTNode>();
		implements_nodes.addAll(node.superInterfaceTypes());
		for (ASTNode implement : implements_nodes)
			this.converter_factory.getConverter(implement).bind(implement);

		Vector<ASTNode> declarations_nodes = new Vector<ASTNode>();
		declarations_nodes.addAll(node.bodyDeclarations());
		for (ASTNode declaration : declarations_nodes)
			this.converter_factory.getConverter(declaration).bind(declaration);

		Vector<ASTNode> modifiers_nodes = new Vector<ASTNode>();
		modifiers_nodes.addAll(node.modifiers());
		for (ASTNode modifier : modifiers_nodes)
			this.converter_factory.getConverter(modifier).bind(modifier);
	}

	public String getName(TypeDeclaration node) {
		String type = "class";
		if (node.isInterface())
			type = "interface";
		return type + " " + node.getName().toString();
	}

}

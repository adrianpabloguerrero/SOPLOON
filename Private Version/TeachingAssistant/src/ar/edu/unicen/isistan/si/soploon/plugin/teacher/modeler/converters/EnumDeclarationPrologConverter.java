
package ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.PrologCode;

public class EnumDeclarationPrologConverter extends NodeConverter<EnumDeclaration> {

	private static final String KEY = "enum_declaration";
	private static final String[] KEYS = new String[] { null, "parent", null, "modifiers", "implements",
			"constants", "declarations", "compilation_unit" };

	public EnumDeclarationPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(EnumDeclaration node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String name = this.quote(node.getName().toString());

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

		Vector<ASTNode> enum_constants_nodes = new Vector<ASTNode>();
		enum_constants_nodes.addAll(node.enumConstants());
		for (ASTNode enum_constant : enum_constants_nodes)
			this.converter_factory.getConverter(enum_constant).convert(enum_constant);
		String enum_constants = this.generateList(enum_constants_nodes);

		Vector<ASTNode> declarations_nodes = new Vector<ASTNode>();
		declarations_nodes.addAll(node.bodyDeclarations());
		for (ASTNode declaration : declarations_nodes)
			this.converter_factory.getConverter(declaration).convert(declaration);
		String declarations = this.generateList(declarations_nodes);

		String unit = this.mapper.getNodeID(node.getRoot());
	
		String[] args = new String[] { id, parent, name, modifiers, implements_ids, enum_constants,
				declarations, unit };
		this.code.addFact(KEY, generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(EnumDeclaration node) {
		this.mapper.setBindingID(node.resolveBinding(), this.mapper.getNodeID(node));

		this.mapper.setParent(node,node);
		
		Vector<ASTNode> implements_nodes = new Vector<ASTNode>();
		implements_nodes.addAll(node.superInterfaceTypes());
		for (ASTNode implement : implements_nodes)
			this.converter_factory.getConverter(implement).bind(implement);

		Vector<ASTNode> enum_constants_nodes = new Vector<ASTNode>();
		enum_constants_nodes.addAll(node.enumConstants());
		for (ASTNode enum_constant : enum_constants_nodes)
			this.converter_factory.getConverter(enum_constant).bind(enum_constant);

		Vector<ASTNode> declarations_nodes = new Vector<ASTNode>();
		declarations_nodes.addAll(node.bodyDeclarations());
		for (ASTNode declaration : declarations_nodes)
			this.converter_factory.getConverter(declaration).bind(declaration);

	}

	public String getName(TypeDeclaration node) {
		return "enum " + node.getName().toString();
	}
}

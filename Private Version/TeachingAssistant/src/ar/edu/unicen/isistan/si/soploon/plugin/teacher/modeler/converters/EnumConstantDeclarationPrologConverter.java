
package ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.PrologCode;

public class EnumConstantDeclarationPrologConverter extends NodeConverter<EnumConstantDeclaration> {

	private static final String KEY = "enum_constant_declaration";
	private static final String[] KEYS = new String[] { null, "parent", null, "modifiers", "arguments",
			"anonymous_class", "compilation_unit" };

	public EnumConstantDeclarationPrologConverter(Mapper mapper, PrologCode code,
			NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(EnumConstantDeclaration node) {
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

		Vector<ASTNode> arguments_nodes = new Vector<ASTNode>();
		arguments_nodes.addAll(node.arguments());
		for (ASTNode argument : arguments_nodes)
			this.converter_factory.getConverter(argument).convert(argument);
		String arguments = this.generateList(arguments_nodes);

		String anonymous_class_id = null;
		if (node.getAnonymousClassDeclaration() != null) {
			this.converter_factory.getConverter(node.getAnonymousClassDeclaration())
					.convert(node.getAnonymousClassDeclaration());
			anonymous_class_id = this.mapper.getNodeID(node.getAnonymousClassDeclaration());
		}

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, name, modifiers, arguments, anonymous_class_id, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));

	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(EnumConstantDeclaration node) {
		this.mapper.setBindingID(node.resolveVariable(), this.mapper.getNodeID(node));

		this.mapper.setParent(node, node);

		Vector<ASTNode> arguments_nodes = new Vector<ASTNode>();
		arguments_nodes.addAll(node.arguments());
		for (ASTNode argument : arguments_nodes)
			this.converter_factory.getConverter(argument).bind(argument);

		if (node.getAnonymousClassDeclaration() != null)
			this.converter_factory.getConverter(node.getAnonymousClassDeclaration())
					.bind(node.getAnonymousClassDeclaration());
	}
	
	public String getName(TypeDeclaration node) {
		return node.getName().toString();
	}

}

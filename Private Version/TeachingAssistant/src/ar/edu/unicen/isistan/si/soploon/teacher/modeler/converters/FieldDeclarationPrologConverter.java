
package ar.edu.unicen.isistan.si.soploon.teacher.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import ar.edu.unicen.isistan.si.soploon.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.teacher.modeler.PrologCode;

public class FieldDeclarationPrologConverter extends NodeConverter<FieldDeclaration> {

	private static final String KEY = "field_declaration";
	private static final String[] KEYS = new String[] { null, "parent", null, "type", "modifiers", "extra_dimensions",
			"initializer", "compilation_unit" };

	public FieldDeclarationPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(FieldDeclaration node) {
		String parent = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getType()).convert(node.getType());
		String type = this.mapper.getNodeID(node.getType());

		Vector<IExtendedModifier> modifiers_nodes = new Vector<IExtendedModifier>();
		modifiers_nodes.addAll(node.modifiers());
		Vector<String> modifiers_names = new Vector<String>();
		for (IExtendedModifier modifier : modifiers_nodes) {
			if (modifier.isModifier())
				modifiers_names.add(this.quote(((Modifier) modifier).getKeyword().toString()));
		}
		String modifiers = modifiers_names.toString();

		String unit = this.mapper.getNodeID(node.getRoot());

		Vector<ASTNode> fragments_nodes = new Vector<ASTNode>();
		fragments_nodes.addAll(node.fragments());
		for (ASTNode aux : fragments_nodes) {
			VariableDeclarationFragment fragment = (VariableDeclarationFragment) aux;

			this.converter_factory.getConverter(fragment).convert(fragment);

			String id = this.mapper.getNodeID(fragment);

			String name = this.quote(fragment.getName().toString());

			String extra_dimensions = Integer.toString(fragment.getExtraDimensions());

			String initializer = null;
			if (fragment.getInitializer() != null)
				initializer = this.mapper.getNodeID(fragment.getInitializer());

			String[] args = new String[] { id, parent, name, type, modifiers, extra_dimensions, initializer, unit };
			this.code.addFact(KEY, this.generateArgs(KEYS, args));
		}

		String new_id = this.generateList(fragments_nodes);
		this.mapper.setNodeID(node, new_id.substring(1, new_id.length() - 1));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(FieldDeclaration node) {
		this.converter_factory.getConverter(node.getType()).bind(node.getType());

		this.mapper.setParent(node,node);

		Vector<VariableDeclarationFragment> fragments_nodes = new Vector<VariableDeclarationFragment>();
		fragments_nodes.addAll(node.fragments());
		for (VariableDeclarationFragment fragment : fragments_nodes)
			this.converter_factory.getConverter(fragment).bind(fragment);

	}

}

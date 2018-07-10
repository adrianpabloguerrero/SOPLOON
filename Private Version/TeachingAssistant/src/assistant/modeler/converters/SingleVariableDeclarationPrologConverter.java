
package assistant.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import assistant.modeler.Mapper;
import assistant.modeler.PrologCode;

public class SingleVariableDeclarationPrologConverter extends NodeConverter<SingleVariableDeclaration> {

	private static final String KEY = "variable_declaration";
	private static final String[] KEYS = new String[] { null, "parent", null, "type", "modifiers", "extra_dimensions",
			"initializer", "body_declaration", "type_declaration", "compilation_unit" };

	public SingleVariableDeclarationPrologConverter(Mapper mapper, PrologCode code,
			NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(SingleVariableDeclaration node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String name = this.quote(node.getName().getIdentifier());

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

		String dimensions = Integer.toString(node.getExtraDimensions());

		String initializer = null;
		if (node.getInitializer() != null)
			initializer = this.mapper.getNodeID(node.getInitializer());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, name, type, modifiers, dimensions, initializer, body_declaration,
				type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(SingleVariableDeclaration node) {
		this.mapper.setBindingID(node.resolveBinding(), this.mapper.getNodeID(node));

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getType()).bind(node.getType());

		if (node.getInitializer() != null)
			this.converter_factory.getConverter(node.getInitializer()).bind(node.getInitializer());
	}

}


package ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.Modifier;

import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.PrologCode;

public class InitializerPrologConverter extends NodeConverter<Initializer> {

	private static final String KEY = "initializer";
	private static final String[] KEYS = new String[] { null, "parent", "modifiers", "body", "compilation_unit" };

	public InitializerPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(Initializer node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		Vector<IExtendedModifier> modifiers_nodes = new Vector<IExtendedModifier>();
		modifiers_nodes.addAll(node.modifiers());
		Vector<String> modifiers_names = new Vector<String>();
		for (IExtendedModifier modifier : modifiers_nodes) {
			if (modifier.isModifier())
				modifiers_names.add(this.quote(((Modifier) modifier).getKeyword().toString()));
		}
		String modifiers = modifiers_names.toString();

		this.converter_factory.getConverter(node.getBody()).convert(node.getBody());
		String body = this.mapper.getNodeID(node.getBody());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, modifiers, body, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(Initializer node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node,node);

		this.converter_factory.getConverter(node.getBody()).bind(node.getBody());
	}

}

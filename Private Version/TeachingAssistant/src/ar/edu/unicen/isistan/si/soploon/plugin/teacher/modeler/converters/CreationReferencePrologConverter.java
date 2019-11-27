
package ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CreationReference;

import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.PrologCode;

public class CreationReferencePrologConverter extends NodeConverter<CreationReference> {

	private static final String KEY = "creation_reference";
	private static final String[] KEYS = new String[] { null, "parent", "type", "constructor", "arguments",
			"body_declaration", "type_declaration", "compilation_unit" };

	public CreationReferencePrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(CreationReference node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getType()).convert(node.getType());
		String type = this.mapper.getNodeID(node.getType());

		String constructor = this.mapper.getBindingID(node.resolveMethodBinding());

		Vector<ASTNode> arguments_nodes = new Vector<ASTNode>();
		arguments_nodes.addAll(node.typeArguments());
		for (ASTNode argument : arguments_nodes)
			this.converter_factory.getConverter(argument).convert(argument);
		String arguments = this.generateList(arguments_nodes);

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, type, constructor, arguments, body_declaration,
				type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(CreationReference node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		Vector<ASTNode> arguments_nodes = new Vector<ASTNode>();
		arguments_nodes.addAll(node.typeArguments());
		for (ASTNode argument : arguments_nodes)
			this.converter_factory.getConverter(argument).bind(argument);

		this.converter_factory.getConverter(node.getType()).bind(node.getType());

	}

}

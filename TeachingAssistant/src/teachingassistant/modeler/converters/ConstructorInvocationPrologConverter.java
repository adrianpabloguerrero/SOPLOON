
package teachingassistant.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ConstructorInvocation;

import teachingassistant.modeler.Mapper;
import teachingassistant.modeler.PrologCode;

public class ConstructorInvocationPrologConverter extends NodeConverter<ConstructorInvocation> {

	private static final String KEY = "constructor_invocation";
	private static final String[] KEYS = new String[] { null, "parent", "constructor", "arguments", "type_arguments",
			"body_declaration", "type_declaration", "compilation_unit" };

	public ConstructorInvocationPrologConverter(Mapper mapper, PrologCode code,
			NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(ConstructorInvocation node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());
		
		String constructor = this.mapper.getBindingID(node.resolveConstructorBinding());

		Vector<ASTNode> arguments_nodes = new Vector<ASTNode>();
		arguments_nodes.addAll(node.arguments());
		for (ASTNode argument : arguments_nodes)
			this.converter_factory.getConverter(argument).convert(argument);
		String arguments = this.generateList(arguments_nodes);

		Vector<ASTNode> arguments_types_nodes = new Vector<ASTNode>();
		arguments_types_nodes.addAll(node.typeArguments());
		for (ASTNode type_argument_node : arguments_types_nodes)
			this.converter_factory.getConverter(type_argument_node).convert(type_argument_node);
		String type_arguments = this.generateList(arguments_types_nodes);

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, constructor, arguments, type_arguments, body_declaration,
				type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(ConstructorInvocation node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		Vector<ASTNode> arguments_nodes = new Vector<ASTNode>();
		arguments_nodes.addAll(node.arguments());
		for (ASTNode argument : arguments_nodes)
			this.converter_factory.getConverter(argument).bind(argument);

		Vector<ASTNode> arguments_types_nodes = new Vector<ASTNode>();
		arguments_types_nodes.addAll(node.typeArguments());
		for (ASTNode type_argument_node : arguments_types_nodes)
			this.converter_factory.getConverter(type_argument_node).bind(type_argument_node);

	}

}

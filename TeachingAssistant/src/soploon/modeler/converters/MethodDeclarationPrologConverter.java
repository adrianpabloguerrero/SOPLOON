package soploon.modeler.converters;

import java.util.List;
import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import soploon.modeler.Mapper;
import soploon.modeler.PrologCode;

public class MethodDeclarationPrologConverter extends NodeConverter<MethodDeclaration> {

	private static final String CONSTRUCTOR_KEY = "constructor_declaration";
	private static final String METHOD_KEY = "method_declaration";

	private static final String[] KEYS = new String[] { null, "parent", null, "modifiers", "parameters",
			"parameters_types", "return_type", "dimensions", "body", "exceptions", "compilation_unit" };

	public MethodDeclarationPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(MethodDeclaration node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String fact = node.isConstructor() ? CONSTRUCTOR_KEY : METHOD_KEY;

		String name = this.quote(node.getName().toString());

		Vector<ASTNode> parameters_nodes = new Vector<ASTNode>();
		Vector<ASTNode> parameters_types_nodes = new Vector<ASTNode>();
		parameters_nodes.addAll(node.parameters());
		for (ASTNode parameter : parameters_nodes) {
			this.converter_factory.getConverter(parameter).convert(parameter);
			parameters_types_nodes.add(((SingleVariableDeclaration) parameter).getType());
		}
		String parameters = this.generateList(parameters_nodes);
		String parameters_types = this.generateList(parameters_types_nodes);

		Vector<IExtendedModifier> modifiers_nodes = new Vector<IExtendedModifier>();
		modifiers_nodes.addAll(node.modifiers());
		Vector<String> modifiers_names = new Vector<String>();
		for (IExtendedModifier modifier : modifiers_nodes) {
			if (modifier.isModifier()) {
				modifiers_names.add(this.quote(((Modifier) modifier).getKeyword().toString()));
			}
		}
		String modifiers = modifiers_names.toString();

		String body_id = null;
		if (node.getBody() != null) {
			this.converter_factory.getConverter(node.getBody()).convert(node.getBody());
			body_id = this.mapper.getNodeID(node.getBody());
		}

		String return_type = null;
		if (node.getReturnType2() != null) {
			this.converter_factory.getConverter(node.getReturnType2()).convert(node.getReturnType2());
			return_type = this.mapper.getNodeID(node.getReturnType2());
		}
		if (node.isConstructor())
			return_type = this.mapper.getNodeID(node.getParent());

		String dimensions = Integer.toString(node.getExtraDimensions());

		Vector<ASTNode> exceptions_nodes = new Vector<ASTNode>();
		exceptions_nodes.addAll(node.thrownExceptionTypes());
		for (ASTNode exception_type : exceptions_nodes)
			this.converter_factory.getConverter(exception_type).convert(exception_type);
		String exceptions_ids = this.generateList(exceptions_nodes);

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, name, modifiers, parameters, parameters_types, return_type,
				dimensions, body_id, exceptions_ids, unit };
		this.code.addFact(fact, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(MethodDeclaration node) {
		this.mapper.setBindingID(node.resolveBinding(), this.mapper.getNodeID(node));

		this.mapper.setParent(node,node);

		Vector<SingleVariableDeclaration> parameters_nodes = new Vector<SingleVariableDeclaration>();
		Vector<ASTNode> arguments_types = new Vector<ASTNode>();
		parameters_nodes.addAll(node.parameters());
		for (SingleVariableDeclaration parameter : parameters_nodes) {
			this.converter_factory.getConverter(parameter).bind(parameter);
			arguments_types.add(parameter.getType());
		}

		for (ASTNode argument : arguments_types)
			this.converter_factory.getConverter(argument).bind(argument);

		this.converter_factory.getConverter(node.getBody()).bind(node.getBody());

		if (node.getReturnType2() != null)
			this.converter_factory.getConverter(node.getReturnType2()).bind(node.getReturnType2());

		Vector<ASTNode> exceptions_nodes = new Vector<ASTNode>();
		exceptions_nodes.addAll(node.thrownExceptionTypes());
		for (ASTNode exception_type : exceptions_nodes)
			this.converter_factory.getConverter(exception_type).bind(exception_type);
	}

	@SuppressWarnings("unchecked")
	public String getName(MethodDeclaration node) {
		List<String> parameters = new Vector<String>();
		for (Object object: node.parameters()) {
			ASTNode parameter = (ASTNode) object;
			parameters.add(this.converter_factory.getConverter(parameter).getName(parameter));
		}
		String params = parameters.toString();
		if (!parameters.isEmpty())
			params = params.substring(1,params.length()-1);
		String type = "";
		if (!node.isConstructor())
			type = this.converter_factory.getConverter(node.getReturnType2()).getName(node.getReturnType2());
		return type + " " + node.getName().toString() + "(" + params + ")";
	}

}

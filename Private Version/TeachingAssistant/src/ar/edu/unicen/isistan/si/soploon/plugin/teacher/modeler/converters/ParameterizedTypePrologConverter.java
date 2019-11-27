package ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ParameterizedType;

import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.PrologCode;

public class ParameterizedTypePrologConverter extends NodeConverter<ParameterizedType> {

	private static final String KEY = "parameterized_type";
	private final static String[] KEYS = { null, "fqn", "parameters" };

	public ParameterizedTypePrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(ParameterizedType node) {
		String id = this.mapper.getBindingID(node.resolveBinding());

		if (id != null) {
			this.mapper.setNodeID(node, id);
			return;
		}

		id = this.mapper.getNodeID(node);
		this.mapper.setBindingID(node.resolveBinding(), id);
	
		String fqn = this.quote(node.resolveBinding().getTypeDeclaration().getQualifiedName());

		Vector<ASTNode> types_arguments_nodes = new Vector<ASTNode>();
		types_arguments_nodes.addAll(node.typeArguments());
		for (ASTNode type_argument : types_arguments_nodes)
			this.converter_factory.getConverter(type_argument).convert(type_argument);
		String types_arguments = this.generateList(types_arguments_nodes);

		String[] args = new String[] { id, fqn, types_arguments };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(ParameterizedType node) {
		Vector<ASTNode> arguments_nodes = new Vector<ASTNode>();
		arguments_nodes.addAll(node.typeArguments());
		for (ASTNode argument : arguments_nodes)
			this.converter_factory.getConverter(argument).bind(argument);
	}

}

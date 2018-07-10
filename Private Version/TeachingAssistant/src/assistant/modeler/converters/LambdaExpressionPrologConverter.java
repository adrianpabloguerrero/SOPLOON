package assistant.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.LambdaExpression;

import assistant.modeler.Mapper;
import assistant.modeler.PrologCode;

public class LambdaExpressionPrologConverter extends NodeConverter<LambdaExpression> {

	private static final String KEY = "lambda_expression";
	private static final String[] KEYS = new String[] { null, "parent", "parameters", "body", "body_declaration",
			"type_declaration", "compilation_unit" };

	public LambdaExpressionPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(LambdaExpression node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getBody()).convert(node.getBody());
		String body = this.mapper.getNodeID(node.getBody());

		Vector<ASTNode> parameters_nodes = new Vector<ASTNode>();
		parameters_nodes.addAll(node.parameters());
		for (ASTNode parameter : parameters_nodes)
			this.converter_factory.getConverter(parameter).convert(parameter);
		String parameters = this.generateList(parameters_nodes);

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));

		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, parameters, body, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(LambdaExpression node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getBody()).bind(node.getBody());

		Vector<ASTNode> parameters_nodes = new Vector<ASTNode>();
		parameters_nodes.addAll(node.parameters());
		for (ASTNode parameter : parameters_nodes)
			this.converter_factory.getConverter(parameter).bind(parameter);
	}

}

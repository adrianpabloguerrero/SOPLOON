package teachingassistant.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.TryStatement;

import teachingassistant.modeler.Mapper; import teachingassistant.modeler.PrologCode;

public class TryStatementPrologConverter extends NodeConverter<TryStatement> {

	private static final String KEY = "try_statement";
	private static final String[] KEYS = new String[] { null, "parent", "resources", "body", "catchs", "finally",
			"body_declaration", "type_declaration", "compilation_unit" };

	public TryStatementPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(TryStatement node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());

		this.converter_factory.getConverter(node.getBody()).convert(node.getBody());
		String body = this.mapper.getNodeID(node.getBody());

		Vector<ASTNode> resources_nodes = new Vector<ASTNode>();
		resources_nodes.addAll(node.resources());
		for (ASTNode resource: resources_nodes)
    		this.converter_factory.getConverter(resource).convert(resource);  
		String resources = this.generateList(resources_nodes);

		Vector<ASTNode> catchs_nodes = new Vector<ASTNode>();
		catchs_nodes.addAll(node.catchClauses());
		for (ASTNode catch_node: catchs_nodes)
    		this.converter_factory.getConverter(catch_node).convert(catch_node);  
		String catchs = this.generateList(catchs_nodes);

		this.converter_factory.getConverter(node.getFinally()).convert(node.getFinally());
		String finally_body = this.mapper.getNodeID(node.getFinally());

		String body_declaration = this.mapper.getNodeID(this.mapper.getParent(node));
    	
		String type_declaration = this.mapper.getNodeID(this.mapper.getParent(node).getParent());
		
		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent_id, resources, body, catchs, finally_body, body_declaration, type_declaration, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(TryStatement node) {
		this.mapper.getNodeID(node);
		
		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getBody()).bind(node.getBody());

		Vector<ASTNode> resources_nodes = new Vector<ASTNode>();
		resources_nodes.addAll(node.resources());
		for (ASTNode resource: resources_nodes)
    		this.converter_factory.getConverter(resource).bind(resource);  

		Vector<ASTNode> catchs_nodes = new Vector<ASTNode>();
		catchs_nodes.addAll(node.catchClauses());
		for (ASTNode catch_node: catchs_nodes)
    		this.converter_factory.getConverter(catch_node).bind(catch_node);  

		this.converter_factory.getConverter(node.getFinally()).bind(node.getFinally());

	}

}
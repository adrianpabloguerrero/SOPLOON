package teachingassistant.modeler;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IBinding;

public class Mapper {

	private long actual_id;
	private HashMap<ASTNode, String> nodes_ids;
	private HashMap<ASTNode,String> aux_nodes_ids;
	private HashMap<String, ASTNode> nodes_ids_inverse;
	private HashMap<ASTNode,ASTNode> node_levels;
	private HashMap<String, String> bindings_ids;

	public Mapper() {
		this.actual_id = 0;
		this.nodes_ids = new HashMap<ASTNode,String>();
		this.aux_nodes_ids = new HashMap<ASTNode,String>();
		this.nodes_ids_inverse = new HashMap<String,ASTNode>();
		this.node_levels = new HashMap<ASTNode,ASTNode>();
		this.bindings_ids = new HashMap<String,String>();
	}

	public void setNodeID(ASTNode node, String id) {
		if (node != null) {
    		if (this.nodes_ids_inverse.get(id) != null)
    			this.aux_nodes_ids.put(node, id);
    		else {
    			this.nodes_ids.put(node,id);
    			this.nodes_ids_inverse.put(id,node);
    		}
    	}
	}

	public String getNodeID(ASTNode node) {
		if (node == null)
			return null;
    	String id = this.aux_nodes_ids.get(node);
    	if (id == null)
    		id = this.nodes_ids.get(node);
    	if (id == null) {
    		id = Long.toString(actual_id++);
    		setNodeID(node,id);
    	}
    	return id;
	}

	public String setBindingID(IBinding binding, String id) {
		if (binding != null) {
			this.setBindingID(binding.getKey(), id);
			return this.getBindingID(binding);
		}
		return null;
	}

	public String setBindingID(String binding, String id) {
		return this.bindings_ids.put(binding, id);
	}

	public String getBindingID(IBinding binding) {
		if (binding != null)
			return this.bindings_ids.get(binding.getKey());
		return null;
	}

	public String getID() {
		return Long.toString(actual_id++);
	}

	public ASTNode getNode(String id) {
		return this.nodes_ids_inverse.get(id);
	}

	public ASTNode getParent(ASTNode node) {
		return node_levels.get(node);
	}
	
	public void setParent(ASTNode node, ASTNode parent) {
		this.node_levels.put(node,parent);
	}

	public boolean isPrimary(ASTNode node) {
		return nodes_ids.containsKey(node);
	}
}

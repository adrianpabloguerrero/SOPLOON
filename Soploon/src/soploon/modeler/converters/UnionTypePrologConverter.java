package soploon.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.UnionType;

import soploon.modeler.Mapper;
import soploon.modeler.PrologCode;

public class UnionTypePrologConverter extends NodeConverter<UnionType> {

	private static final String KEY = "union_type";
	private final static String[] KEYS = { null, "types" };

	public UnionTypePrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(UnionType node) {
		String id = this.mapper.getBindingID(node.resolveBinding());

		if (id != null) {
			this.mapper.setNodeID(node, id);
			return;
		}

		id = this.mapper.getNodeID(node);
		this.mapper.setBindingID(node.resolveBinding(), id);
		
		Vector<ASTNode> types_nodes = new Vector<ASTNode>();
		types_nodes.addAll(node.types());
		for (ASTNode type : types_nodes)
			this.converter_factory.getConverter(type).convert(type);
		String types = this.generateList(types_nodes);

		String[] args = new String[] { id, types };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(UnionType node) {
		Vector<ASTNode> types_nodes = new Vector<ASTNode>();
		types_nodes.addAll(node.types());
		for (ASTNode type : types_nodes)
			this.converter_factory.getConverter(type).bind(type);
	}

}


package assistant.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

import assistant.modeler.Mapper;
import assistant.modeler.PrologCode;

public class CompilationUnitPrologConverter extends NodeConverter<CompilationUnit> {

	private static final String KEY = "compilation_unit";
	private final static String[] KEYS = { null, null, "path", "package", "imports", "types" };

	protected CompilationUnitPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(CompilationUnit node) {
		String id = this.mapper.getNodeID(node);

		String name = this.quote(node.getJavaElement().getElementName().toString());

		String path = this.quote(node.getJavaElement().getPath().toString());

		String package_id = null;
		if (node.getPackage() != null) {
			this.converter_factory.getConverter(node.getPackage()).convert(node.getPackage());
			package_id = this.mapper.getNodeID(node.getPackage());
		}

		Vector<ASTNode> import_nodes = new Vector<ASTNode>();
		import_nodes.addAll(node.imports());
		for (ASTNode import_node : import_nodes)
			this.converter_factory.getConverter(import_node).convert(import_node);
		String imports = this.generateList(import_nodes);

		Vector<ASTNode> types_nodes = new Vector<ASTNode>();
		types_nodes.addAll(node.types());
		for (ASTNode type : types_nodes)
			this.converter_factory.getConverter(type).convert(type);
		String types = this.generateList(types_nodes);

		String[] args = new String[] { id, name, path, package_id, imports, types };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(CompilationUnit node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node,node);

		if (node.getPackage() != null)
			this.converter_factory.getConverter(node.getPackage()).bind(node.getPackage());

		Vector<ASTNode> import_nodes = new Vector<ASTNode>();
		import_nodes.addAll(node.imports());
		for (ASTNode import_node : import_nodes)
			this.converter_factory.getConverter(import_node).bind(import_node);

		Vector<ASTNode> types_nodes = new Vector<ASTNode>();
		types_nodes.addAll(node.types());
		for (ASTNode type : types_nodes)
			this.converter_factory.getConverter(type).bind(type);

	}

}

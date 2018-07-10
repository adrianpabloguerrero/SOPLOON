
package soploon.modeler.converters;

import org.eclipse.jdt.core.dom.ImportDeclaration;

import soploon.modeler.Mapper;
import soploon.modeler.PrologCode;

public class ImportDeclarationPrologConverter extends NodeConverter<ImportDeclaration> {

	private static final String KEY = "import_declaration";
	private final static String[] KEYS = { null, "parent", "fqn", "on_demand", "static" };

	public ImportDeclarationPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@Override
	public void convert(ImportDeclaration node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String fqn = this.quote(node.getName().getFullyQualifiedName().toString());

		String is_on_demand = Boolean.toString(node.isOnDemand());

		String is_static = Boolean.toString(node.isStatic());

		String[] args = new String[] { id, parent, fqn, is_on_demand, is_static };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@Override
	public void bind(ImportDeclaration node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));
	}
}


package soploon.modeler.converters;

import org.eclipse.jdt.core.dom.PackageDeclaration;

import soploon.modeler.Mapper;
import soploon.modeler.PrologCode;

public class PackageDeclarationPrologConverter extends NodeConverter<PackageDeclaration> {

	private static final String KEY = "package_declaration";
	private static final String[] KEYS = new String[] { null, "parent", "package" };

	public PackageDeclarationPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(PackageDeclaration node) {
		String id = this.mapper.getNodeID(node);
		
		String parent = this.mapper.getNodeID(node.getParent());
	
		this.converter_factory.getConverter(node.getName()).convert(node.getName());
		String package_id = this.mapper.getNodeID(node.getName());
	
		String[] args = new String[] { id, parent, package_id };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(PackageDeclaration node) {
		this.mapper.getNodeID(node);
		
		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));
		
		this.converter_factory.getConverter(node.getName()).bind(node.getName());
	}
}

package soploon.modeler.converters;

import org.eclipse.jdt.core.dom.TypeDeclarationStatement;

import soploon.modeler.Mapper;
import soploon.modeler.PrologCode;

public class TypeDeclarationStatementPrologConverter extends NodeConverter<TypeDeclarationStatement> {

	public TypeDeclarationStatementPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(TypeDeclarationStatement node) {
		this.converter_factory.getConverter(node.getDeclaration()).convert(node.getDeclaration());
	
		this.mapper.setNodeID(node, this.mapper.getNodeID(node.getDeclaration()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(TypeDeclarationStatement node) {
		this.mapper.setNodeID(node, this.mapper.getNodeID(node.getParent()));

		this.converter_factory.getConverter(node.getDeclaration()).bind(node.getDeclaration());
	}
}

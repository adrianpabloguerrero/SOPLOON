package ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.converters;

import org.eclipse.jdt.core.dom.TypeDeclarationStatement;

import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.PrologCode;

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

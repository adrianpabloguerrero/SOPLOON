package ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.converters;

import org.eclipse.jdt.core.dom.ExpressionStatement;

import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.PrologCode;

public class ExpressionStatementPrologConverter extends NodeConverter<ExpressionStatement> {

	public ExpressionStatementPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(ExpressionStatement node) {
		this.converter_factory.getConverter(node.getExpression()).convert(node.getExpression());

		this.mapper.setNodeID(node, this.mapper.getNodeID(node.getExpression()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(ExpressionStatement node) {
		this.mapper.setNodeID(node, this.mapper.getNodeID(node.getParent()));

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getExpression()).bind(node.getExpression());
	}
}

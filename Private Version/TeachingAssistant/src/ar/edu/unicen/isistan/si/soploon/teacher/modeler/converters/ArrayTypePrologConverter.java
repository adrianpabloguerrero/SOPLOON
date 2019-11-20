package ar.edu.unicen.isistan.si.soploon.teacher.modeler.converters;

import org.eclipse.jdt.core.dom.ArrayType;

import ar.edu.unicen.isistan.si.soploon.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.teacher.modeler.PrologCode;

public class ArrayTypePrologConverter extends NodeConverter<ArrayType> {

	private static final String KEY = "array_type";
	private static final String[] KEYS = { null, "array_of", "dimensions" };

	public ArrayTypePrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(ArrayType node) {
		String id = this.mapper.getBindingID(node.resolveBinding());

		if (id != null) {
			this.mapper.setNodeID(node, id);
			return;
		}

		id = this.mapper.getNodeID(node);
		this.mapper.setBindingID(node.resolveBinding(), id);

		this.converter_factory.getConverter(node.getElementType()).convert(node.getElementType());
		String type = this.mapper.getNodeID(node.getElementType());

		String dimensions = Integer.toString(node.getDimensions());

		String[] args = new String[] { id, type, dimensions };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));

	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(ArrayType node) {
		this.converter_factory.getConverter(node.getElementType()).bind(node.getElementType());
	}
}

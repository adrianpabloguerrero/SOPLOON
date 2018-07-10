
package soploon.modeler.converters;

import org.eclipse.jdt.core.dom.PrimitiveType;

import soploon.modeler.Mapper;
import soploon.modeler.PrologCode;

public class PrimitiveTypePrologConverter extends NodeConverter<PrimitiveType> {

	private static final String KEY = "primitive_type";
	private static final String[] KEYS = { null, "code" };

	public PrimitiveTypePrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@Override
	public void convert(PrimitiveType node) {
		String id = this.mapper.getBindingID(node.resolveBinding());

		if (id != null) {
			this.mapper.setNodeID(node, id);
			return;
		}

		id = this.mapper.getNodeID(node);
		this.mapper.setBindingID(node.resolveBinding(), id);
				
		String code = this.quote(node.getPrimitiveTypeCode().toString());

		String[] args = new String[] { id, code };
		this.code.addFact(KEY, this.generateArgs(KEYS,args));
	}

	@Override
	public void bind(PrimitiveType node) {

	}
}

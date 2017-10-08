
package teachingassistant.modeler.converters;

import org.eclipse.jdt.core.dom.AnnotatableType;

import teachingassistant.modeler.Mapper; import teachingassistant.modeler.PrologCode;


public class AnnotatableTypePrologConverter extends NodeConverter<AnnotatableType> {

	private final static String KEY = "type";
	private final static String[] KEYS = { null, "name" };

	public AnnotatableTypePrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@Override
	public void convert(AnnotatableType node) {
		String id = this.mapper.getBindingID(node.resolveBinding());

		if (id != null) {
			this.mapper.setNodeID(node, id);
			return;
		}

		id = this.mapper.getNodeID(node);
		this.mapper.setBindingID(node.resolveBinding(), id);

		String name = this.quote(node.resolveBinding().getName());
		
		String[] args = new String[] { id, name };
		
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@Override
	public void bind(AnnotatableType node) {
	}

}

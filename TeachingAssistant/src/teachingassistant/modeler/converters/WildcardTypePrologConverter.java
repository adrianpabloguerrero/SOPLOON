package teachingassistant.modeler.converters;

import org.eclipse.jdt.core.dom.WildcardType;

import teachingassistant.modeler.Mapper; import teachingassistant.modeler.PrologCode;

public class WildcardTypePrologConverter extends NodeConverter<WildcardType> {

	private static final String KEY = "wildcard_type";
	private final static String[] KEYS = { null, "bound", "is_upper_bound" };

	public WildcardTypePrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(WildcardType node) {
		String id = this.mapper.getBindingID(node.resolveBinding());

		if (id != null) {
			this.mapper.setNodeID(node, id);
			return;
		}
		
		id = this.mapper.getNodeID(node);
		this.mapper.setBindingID(node.resolveBinding(), id);

		this.converter_factory.getConverter(node.getBound()).convert(node.getBound());
		String bound = this.mapper.getNodeID(node.getBound());

		String is_upper_bound = Boolean.toString(node.isUpperBound());

		String[] args = new String[] { id, bound, is_upper_bound };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(WildcardType node) {
		this.mapper.getNodeID(node);

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		this.converter_factory.getConverter(node.getBound()).bind(node.getBound());
	}
}


package ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.converters;

import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.SimpleName;

import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.PrologCode;

public class SimpleNamePrologConverter extends NodeConverter<SimpleName> {

	private static final String[] KINDS = new String[] { "package", "type", "variable", "method", "annotation",
			"member_value_pair" };
	private static final String[][] KEYS = new String[][] { 
		{ null, "name" },
		{ null, "name" },
		{ null, "name", "type" },
		{ null, "name", "type", "return_type" },
		{ null, "name" }
	};

	public SimpleNamePrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@Override
	public void convert(SimpleName node) {
		String id = this.mapper.getBindingID(node.resolveBinding());

		if (id != null) {
			this.mapper.setNodeID(node, id);
			return;
		}

		id = this.mapper.getNodeID(node);
		this.mapper.setBindingID(node.resolveBinding(), id);

		IBinding binding = node.resolveBinding();
		if (binding != null) {
			String kind = KINDS[binding.getKind() - 1];
			String[] keys = KEYS[binding.getKind() - 1];
			String[] args = null;
			String name = this.quote(binding.getName());
			switch (node.resolveBinding().getKind()) {
			
			case IBinding.METHOD:
				IMethodBinding method_bind = (IMethodBinding) binding;
				String type = this.mapper.getBindingID(method_bind.getDeclaringClass());
				String return_type = this.mapper.getBindingID(method_bind.getReturnType());
				args = new String[]{ id, name, type, return_type };
				break;
			case IBinding.VARIABLE:
				IVariableBinding variable_bind = (IVariableBinding) binding;
				type = this.mapper.getBindingID(variable_bind.getType());
				args = new String[]{ id, name, type };
				break;
			case IBinding.TYPE:
			case IBinding.ANNOTATION:
			case IBinding.PACKAGE:
			case IBinding.MEMBER_VALUE_PAIR:
				args = new String[]{ id, name };
				break;
			}
			this.code.addFact(kind, this.generateArgs(keys, args));
		}

	}

	@Override
	public void bind(SimpleName node) {

	}

}

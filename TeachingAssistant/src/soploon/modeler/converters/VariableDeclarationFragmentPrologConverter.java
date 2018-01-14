package soploon.modeler.converters;

import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import soploon.modeler.Mapper;
import soploon.modeler.PrologCode;

public class VariableDeclarationFragmentPrologConverter extends NodeConverter<VariableDeclarationFragment> {
   	

	public VariableDeclarationFragmentPrologConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
        super(mapper, code, converter_factory);
    }

    @SuppressWarnings("unchecked")
	@Override
    public void convert(VariableDeclarationFragment node) {   	
    	if (node.getInitializer() != null)
    		this.converter_factory.getConverter(node.getInitializer()).convert(node.getInitializer());
    }

	@SuppressWarnings("unchecked")
	@Override
	public void bind(VariableDeclarationFragment node) {
		this.mapper.setBindingID(node.resolveBinding(), this.mapper.getNodeID(node));

		this.mapper.setParent(node, this.mapper.getParent(node.getParent()));

		if (node.getInitializer() != null)
	   		this.converter_factory.getConverter(node.getInitializer()).bind(node.getInitializer());
	}
	
}


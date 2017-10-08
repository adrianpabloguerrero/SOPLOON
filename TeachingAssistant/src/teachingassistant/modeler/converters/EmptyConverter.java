
package teachingassistant.modeler.converters;

import org.eclipse.jdt.core.dom.ASTNode;

import teachingassistant.modeler.Mapper; import teachingassistant.modeler.PrologCode;

public class EmptyConverter extends NodeConverter<ASTNode> {
   
	public EmptyConverter(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory) {
        super(mapper, code, converter_factory);
    }

    @Override
    public void convert(ASTNode node) {
    }

	@Override
	public void bind(ASTNode node) {
	}

}


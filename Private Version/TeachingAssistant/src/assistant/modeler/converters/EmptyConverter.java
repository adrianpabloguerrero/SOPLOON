
package assistant.modeler.converters;

import org.eclipse.jdt.core.dom.ASTNode;

import assistant.modeler.Mapper;
import assistant.modeler.PrologCode;

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


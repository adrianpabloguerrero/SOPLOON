
package ar.edu.unicen.isistan.si.soploon.teacher.modeler.converters;

import org.eclipse.jdt.core.dom.ASTNode;

import ar.edu.unicen.isistan.si.soploon.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.teacher.modeler.PrologCode;

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


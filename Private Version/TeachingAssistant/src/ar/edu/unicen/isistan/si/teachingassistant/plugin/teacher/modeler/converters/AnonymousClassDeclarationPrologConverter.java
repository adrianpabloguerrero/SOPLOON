package ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.converters;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;

import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.PrologCode;

public class AnonymousClassDeclarationPrologConverter extends NodeConverter<AnonymousClassDeclaration> {

	private static final String KEY = "anonymous_class_declaration";
	private static final String[] KEYS = new String[] { null, "parent", "declarations", "compilation_unit" };

	public AnonymousClassDeclarationPrologConverter(Mapper mapper, PrologCode code,
			NodeConverterFactory converter_factory) {
		super(mapper, code, converter_factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(AnonymousClassDeclaration node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		Vector<ASTNode> declarations_nodes = new Vector<ASTNode>();
		declarations_nodes.addAll(node.bodyDeclarations());
		for (ASTNode declaration : declarations_nodes)
			this.converter_factory.getConverter(declaration).convert(declaration);
		String declarations = this.generateList(declarations_nodes);

		String unit = this.mapper.getNodeID(node.getRoot());

		String[] args = new String[] { id, parent, declarations, unit };
		this.code.addFact(KEY, this.generateArgs(KEYS, args));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(AnonymousClassDeclaration node) {
		this.mapper.setBindingID(node.resolveBinding(), this.mapper.getNodeID(node));

		this.mapper.setParent(node,node);

		Vector<ASTNode> declarations_nodes = new Vector<ASTNode>();
		declarations_nodes.addAll(node.bodyDeclarations());
		for (ASTNode declaration : declarations_nodes)
			this.converter_factory.getConverter(declaration).bind(declaration);
	}

}

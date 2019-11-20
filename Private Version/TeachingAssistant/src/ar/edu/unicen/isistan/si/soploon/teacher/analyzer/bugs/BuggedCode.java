
package ar.edu.unicen.isistan.si.soploon.teacher.analyzer.bugs;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.ITextEditor;

import ar.edu.unicen.isistan.si.soploon.teacher.modeler.converters.NodeConverterFactory;

public class BuggedCode {

	private Bug bug;
	private ASTNode node;
	private NodeConverterFactory converter_factory; 
	
	public BuggedCode(Bug bug, ASTNode node, NodeConverterFactory converter_factory) {
		this.bug = bug;
		this.node = node;
		this.converter_factory = converter_factory;
	}
	
	@SuppressWarnings("unchecked")
	public String getLineNumber() {
		return converter_factory.getConverter(node).getLineNumber(node);
	}

	public String getFile() {
		return ((CompilationUnit) node.getRoot()).getJavaElement().getElementName();
	}

	public IPath getPath() {
		return ((CompilationUnit)node.getRoot()).getJavaElement().getPath();
	}
	
	public void open() {
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(this.getPath());
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		try {
			IEditorPart editor = IDE.openEditor(page, file);
			if (editor instanceof ITextEditor) {
			    ITextEditor textEditor = (ITextEditor) editor ;
			    textEditor.selectAndReveal(this.node.getStartPosition(),this.node.getLength());
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
	public ASTNode getNode() {
		return this.node;
	}

	public Bug getBug() {
		return this.bug;
	}

	@SuppressWarnings("unchecked")
	public String getCode() {
		return converter_factory.getConverter(node).getName(node);
	}

	public String toString() {
		return this.getFile() + ", " + this.getLineNumber() + ", " + this.getCode();
	}
}

package ar.edu.unicen.isistan.si.soploon.plugin.teacher.analyzer.bugs;

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

import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.converters.NodeConverterFactory;
import ar.edu.unicen.isistan.si.soploon.server.models.CodeLocation;

public class BuggedCode {

	private Bug bug;
	private ASTNode node;
	private NodeConverterFactory converterFactory; 
	private int nodeId;
	
	public BuggedCode(Bug bug, int nodeId, Mapper mapper, NodeConverterFactory converterFactory) {
		this.bug = bug;
		this.nodeId = nodeId;
		this.node = mapper.getNode(nodeId);
		this.converterFactory = converterFactory;
	}
	
	@SuppressWarnings("unchecked")
	public String getLineNumber() {
		return converterFactory.getConverter(node).getLineNumber(node);
	}

	public String getFile() {
		return ((CompilationUnit) node.getRoot()).getJavaElement().getElementName();
	}

	public IPath getPath() {
		return ((CompilationUnit) node.getRoot()).getJavaElement().getPath();
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
	
	public int getNodeID() {
		return nodeId;
	}

	@SuppressWarnings("unchecked")
	public String getCode() {
		return converterFactory.getConverter(node).getName(node);
	}
	
	public String toString() {
		return this.getFile() + ", " + this.getLineNumber() + ", " + this.getCode();
	}
	
	public CodeLocation toCodeLocation() {
		CodeLocation codeLocation = new CodeLocation();
		codeLocation.setPath(this.getPath().toString());
		codeLocation.setStartChar(this.node.getStartPosition());
		codeLocation.setStartChar(this.node.getStartPosition() + this.node.getLength());
		return codeLocation;
	}
	
}

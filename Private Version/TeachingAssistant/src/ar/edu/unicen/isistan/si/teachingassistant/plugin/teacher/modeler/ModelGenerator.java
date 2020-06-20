package ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.converters.NodeConverterFactory;

public class ModelGenerator {

	private static final String MONITOR_TITLE = "Traduciendo arbol sintactico";

	private PrologCode prologCode;
	private Mapper mapper;
	private NodeConverterFactory converterFactory;

	public ModelGenerator() {
		
	}

	private void init() {
		this.prologCode = new PrologCode();
		this.mapper = new Mapper();
		this.converterFactory = new NodeConverterFactory(this.mapper,this.prologCode);
	}
	
	@SuppressWarnings("unchecked")
	public int process(List<CompilationUnit> units, IProgressMonitor monitor) {
		init();
		try {
			monitor.beginTask(MONITOR_TITLE, units.size() * 2);
			
			for (CompilationUnit unit : units) {
				this.converterFactory.getConverter(unit).bind(unit);
				monitor.worked(1);
				if (monitor.isCanceled()) {
					monitor.done();
					return 1;
				}
			}
			
			for (CompilationUnit unit : units) {
				this.converterFactory.getConverter(unit).convert(unit);
				monitor.worked(1);
				if (monitor.isCanceled()) {
					monitor.done();
					return 1;
				}
			}
								
			return 0;			
		}
		catch (Exception e) {
			monitor.done();
			return -1;
		}
	}

	public PrologCode getPrologCode() {
		return this.prologCode;
	}
		
	public Mapper getMapper() {
		return this.mapper;
	}
	
	public NodeConverterFactory getFactory() {
		return this.converterFactory;
	}
	
	public ArrayList<String> toRepresentation() {
		return this.prologCode.getFacts();
	}
	
	/* Este c�digo modifica un AST y baja las modificaciones del c�digo al archivo */
	/* Va a ser util cuando el programa pueda hacer refactoring */
	/*public void test(CompilationUnit cu) {
		cu.recordModifications();
		String id = "26";
		ASTNode aux = this.generationToolBox.getNode(id);
		MethodDeclaration node = (MethodDeclaration) aux;
		((ASTNode) node.parameters().get(0)).delete();
		String id2 = "41";
		MethodInvocation invocation = (MethodInvocation) this.generationToolBox.getNode(id2);
		((ASTNode) invocation.arguments().get(0)).delete();
		ITextFileBufferManager bufferManager = FileBuffers.getTextFileBufferManager();
		IPath path = cu.getJavaElement().getPath();
		try {
			bufferManager.connect(path, LocationKind.IFILE, null);
			ITextFileBuffer textFileBuffer = bufferManager.getTextFileBuffer(path,LocationKind.IFILE);
			IDocument document = textFileBuffer.getDocument();
			TextEdit edits = cu.rewrite(document, null);
			edits.apply(document);
			textFileBuffer.commit(null, true);
		} catch (CoreException | BadLocationException | MalformedTreeException e ) {
			e.printStackTrace();
		} finally {
			try {
				bufferManager.disconnect(path, LocationKind.IFILE, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}*/

}

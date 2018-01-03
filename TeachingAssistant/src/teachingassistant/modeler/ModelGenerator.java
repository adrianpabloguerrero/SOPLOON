package teachingassistant.modeler;

import java.util.List;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

import teachingassistant.modeler.converters.NodeConverterFactory;

public class ModelGenerator {

	private static final String MONITOR_TITLE = "Converting AST to Prolog";

	private PrologCode check_code;
	private Mapper mapper;
	private NodeConverterFactory converter_factory;

	public ModelGenerator() {
	}

	private void init() {
		this.check_code = new PrologCode();
		this.mapper = new Mapper();
		this.converter_factory = new NodeConverterFactory(this.mapper,this.check_code);
	}
	
	@SuppressWarnings("unchecked")
	public int process(List<CompilationUnit> units, IProgressMonitor monitor) {
		init();
		try {
			monitor.beginTask(MONITOR_TITLE, units.size() * 2);
			
			for (CompilationUnit unit : units) {
				this.converter_factory.getConverter(unit).bind(unit);
				monitor.worked(1);
				if (monitor.isCanceled()) {
					monitor.done();
					return 1;
				}
			}
			
			for (CompilationUnit unit : units) {
				this.converter_factory.getConverter(unit).convert(unit);
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

	public PrologCode getCheckCode() {
		return this.check_code;
	}
		
	public Mapper getMapper() {
		return this.mapper;
	}
	
	public NodeConverterFactory getFactory() {
		return this.converter_factory;
	}
	
	/* Este código modifica un AST y baja las modificaciones del código al archivo */
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

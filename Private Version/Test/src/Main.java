import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.FileASTRequestor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class Main {

	private List<String> paths;
	private List<String> charset;
	private Map<String, CompilationUnit> source_paths;
	
	private void listFiles(File folder) {
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				paths.add(fileEntry.getAbsolutePath());
				listFiles(fileEntry);
			} else if (fileEntry.getName().endsWith(".java")){
				charset.add("UTF-8");
				source_paths.put(fileEntry.getAbsolutePath(), null);
			}
		}
	}


	public void createCompilationUnitsForJavaFiles(File root_folder) {
		this.paths = new ArrayList<String>();
		this.paths.add(root_folder.getAbsolutePath());
		this.source_paths = new HashMap<String, CompilationUnit>();
		this.charset = new ArrayList<String>();
		listFiles(root_folder);
		
		ASTParser parser = ASTParser.newParser(AST.JLS10);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setStatementsRecovery(true);
		parser.setEnvironment(new String[] { root_folder.getAbsolutePath() },
				new String[] { root_folder.getAbsolutePath() }, new String[] { "UTF-8" }, true);

		Hashtable<String, String> options = JavaCore.getOptions();
		options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8);
		options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);

		parser.setCompilerOptions(options);
		
		FileASTRequestor requestor = new FileASTRequestor() {
			
			public void acceptAST(String path, CompilationUnit ast) {
				source_paths.put(path, ast);
			}
		};

		parser.createASTs(source_paths.keySet().toArray(new String[0]), charset.toArray(new String[0]),
				new String[] { "" }, requestor, null);
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.createCompilationUnitsForJavaFiles(new File("./data"));
		Object o = ((FieldDeclaration)((TypeDeclaration) (m.source_paths.values().toArray(new CompilationUnit[m.source_paths.size()])[1].types().get(0))).bodyDeclarations().get(0)).getType().resolveBinding();
		System.out.println(o);
		
	}
}
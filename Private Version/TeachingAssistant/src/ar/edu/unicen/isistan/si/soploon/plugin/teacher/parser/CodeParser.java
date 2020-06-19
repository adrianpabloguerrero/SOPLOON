
package ar.edu.unicen.isistan.si.soploon.plugin.teacher.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import ar.edu.unicen.isistan.si.soploon.server.models.SourceCode;

public class CodeParser {

	private static final String MONITOR_TITLE = "Generando arbol sintactico";

	private ASTParser parser;
	private HashMap<CompilationUnit,String> units;
	
	@SuppressWarnings("deprecation")
	public CodeParser() {
		this.parser = ASTParser.newParser(AST.JLS8);
		this.parser.setKind(ASTParser.K_COMPILATION_UNIT);
		this.units = new HashMap<CompilationUnit,String>();
	}

	private CompilationUnit parse(ICompilationUnit compilation_unit) {
		this.parser.setSource(compilation_unit);
		this.parser.setResolveBindings(true);
		CompilationUnit c = (CompilationUnit) this.parser.createAST(null);
		return c;
	}

	public int process(IJavaProject project, IProgressMonitor monitor) {
		this.units.clear();
		
		try {
			
			IPackageFragment[] packages = project.getPackageFragments();

			Vector<IPackageFragment> packages_source = new Vector<IPackageFragment>();
			for (IPackageFragment actual_package : packages)
				if (actual_package.getKind() == IPackageFragmentRoot.K_SOURCE)
					packages_source.add(actual_package);

			if (monitor.isCanceled())
				monitor.done();

			monitor.beginTask(MONITOR_TITLE, packages_source.size());

			for (IPackageFragment actual_package : packages_source) {
				ICompilationUnit[] compilation_units;
				compilation_units = actual_package.getCompilationUnits();
				for (ICompilationUnit icompilationUnit : compilation_units) {
					CompilationUnit compilationUnit = this.parse(icompilationUnit);
					this.units.put(compilationUnit, icompilationUnit.getSource());

					if (monitor.isCanceled()) {
						monitor.done();
						return 1;
					}
				}
				monitor.worked(1);
			}

			return 0;
		} catch (Exception e) {
			monitor.done();
			return -1;
		}
	}

	public ArrayList<CompilationUnit> getUnits() {
		return new ArrayList<CompilationUnit>(this.units.keySet());
	}

	public ArrayList<SourceCode> toSourceCodes() {
		ArrayList<SourceCode> sourceCodes = new ArrayList<SourceCode>();
		for (CompilationUnit cunit: this.units.keySet()) {
			SourceCode sourceCode = new SourceCode();
			sourceCode.setPath(cunit.getJavaElement().getPath().toString());
			sourceCode.setCode(this.units.get(cunit));
			sourceCodes.add(sourceCode);
		}
		return sourceCodes;
	}

}

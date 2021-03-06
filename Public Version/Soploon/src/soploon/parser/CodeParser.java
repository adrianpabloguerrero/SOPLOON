
package soploon.parser;

import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class CodeParser {

	private static final String MONITOR_TITLE = "Converting Java to AST";

	private ASTParser parser;
	private List<CompilationUnit> units;

	@SuppressWarnings("deprecation")
	public CodeParser() {
		this.parser = ASTParser.newParser(AST.JLS8);
		this.parser.setKind(ASTParser.K_COMPILATION_UNIT);
		this.units = new Vector<CompilationUnit>();
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
				for (ICompilationUnit compilation_unit : compilation_units) {
					this.units.add(this.parse(compilation_unit));

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

	public List<CompilationUnit> getUnits() {
		return this.units;
	}



}

package soploon.teacher;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import soploon.analyzer.PrologAnalyzer;
import soploon.analyzer.bugs.Bug;
import soploon.modeler.ModelGenerator;
import soploon.modeler.NodesCounter;
import soploon.parser.CodeParser;
import soploon.views.CorrectionsView;
import soploon.views.TranslationView;

public class Teacher {

	private static final String ERROR_MESSAGE_X0 = "Error when openning the Project";
	private static final String ERROR_MESSAGE_X1 = "Error when generating the Prolog code";
	private static final String ERROR_MESSAGE_X2 = "Error when generating the AST";
	private static final String ERROR_MESSAGE_X3 = "Error checking the Prolog code";

	private static Teacher TEACHER = null;
	private CodeParser code_parser;
	private PrologAnalyzer prolog_analyzer;
	private ModelGenerator model_generator;

	public static Teacher getInstance() {
		if (TEACHER == null)
			TEACHER = new Teacher();
		return TEACHER;
	}

	private Teacher() {
		this.code_parser = new CodeParser();
		this.prolog_analyzer = new PrologAnalyzer();
		this.model_generator = new ModelGenerator();
	}

	public void init() {
		this.prolog_analyzer.init();	
	}
	
	public PrologAnalyzer getAnalyzer() {
		return this.prolog_analyzer;
	}

	public void check(IJavaProject project) {
		try {
			ProgressMonitorDialog dialog = new ProgressMonitorDialog(new Shell());
		
			CheckRunnable runner = new CheckRunnable(project, code_parser, prolog_analyzer, model_generator);
			dialog.run(true, true, runner);

			int result = runner.getResult();
			if (result == 0) {
				TranslationView translation = (TranslationView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage().showView(TranslationView.ID);
				CorrectionsView corrections = (CorrectionsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage().showView(CorrectionsView.ID);
				corrections.setBugs(runner.getBugs());
				int cant_ast = runner.getASTNodes();
				ArrayList<String> facts = runner.getFacts();
				translation.setContent(cant_ast, facts);
			}
			else if (result < 0) {
				String message = null;
				switch (runner.getResult()) {
				case -1:
					message = ERROR_MESSAGE_X0;
					break;
				case -10:
					message = ERROR_MESSAGE_X1;
					break;
				case -20:
					message = ERROR_MESSAGE_X2;
					break;
				case -30:
					message = ERROR_MESSAGE_X3;
					break;
				}
				// TODO Cambiar los nombres
				MessageDialog.openInformation(null, "Ayudante Virtual", message);
			}
		} catch (InvocationTargetException | InterruptedException | PartInitException e) {
			e.printStackTrace();
		}
	}

	private class CheckRunnable implements IRunnableWithProgress {

		private IJavaProject project;
		private CodeParser code_parser;
		private PrologAnalyzer prolog_analyzer;
		private ModelGenerator model_generator;
		private List<Bug> bugs;
		private int result;
		private int cant_ast_nodes;
	
		public CheckRunnable(IJavaProject project, CodeParser code_parser, PrologAnalyzer prolog_analyzer,
				ModelGenerator model_generator) {
			this.project = project;
			this.code_parser = code_parser;
			this.prolog_analyzer = prolog_analyzer;
			this.model_generator = model_generator;
			this.bugs = new Vector<Bug>();
			this.cant_ast_nodes = 0;
		}

		public ArrayList<String> getFacts() {
			return this.model_generator.getCheckCode().getFacts();
		}

		public int getASTNodes() {
			return this.cant_ast_nodes;
		}
		
		public List<Bug> getBugs() {
			return this.bugs;
		}

		@Override
		public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
			try {						
				// Obtengo todos los paquetes
				IPackageFragment[] packages = this.project.getPackageFragments();

				// Obtengo solo los paquetes que contengan código
				Vector<IPackageFragment> packages_source = new Vector<IPackageFragment>();
				for (IPackageFragment actual_package : packages)
					if (actual_package.getKind() == IPackageFragmentRoot.K_SOURCE)
						packages_source.add(actual_package);

				result = this.code_parser.process(this.project, monitor) * 10;

				NodesCounter counter = new NodesCounter();
				for (CompilationUnit unit: this.code_parser.getUnits())
					unit.accept(counter);
				this.cant_ast_nodes = counter.getCount();
				
				if (result < 0) { // Error
					return;
				}

				if (result > 0) { // Cancelado por el usuario
					return;
				}

				result = this.model_generator.process(this.code_parser.getUnits(), monitor) * 20;
				
				if (result < 0) { // Error
					return;
				}

				if (result > 0) { // Cancelado por el usuario
					return;
				}

				result = this.prolog_analyzer.process(this.model_generator.getMapper(),
						this.model_generator.getCheckCode(), this.model_generator.getFactory(), monitor) * 30;

				if (result < 0) { // Error
					return;
				}

				if (result > 0) { // Cancelado por el usuario
					return;
				}

				this.bugs.addAll(this.prolog_analyzer.getBugs());

				monitor.done();
			} catch (CoreException e) {
				monitor.done();
				result = -1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public int getResult() {
			return this.result;
		}

	}

}

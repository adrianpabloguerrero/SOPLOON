package ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import ar.edu.unicen.isistan.si.soploon.server.models.Configuration;
import ar.edu.unicen.isistan.si.soploon.server.models.Correction;
import ar.edu.unicen.isistan.si.soploon.server.models.Project;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.TeachingAssistant;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.api.Synchronizer;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.eclipse.views.CorrectionsView;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.storage.CorrectionData;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.storage.StorageManager;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.analyzer.PrologAnalyzer;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.analyzer.bugs.Bug;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.modeler.ModelGenerator;
import ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.parser.CodeParser;

public class Teacher {

	public static final String LOG_PATH = (Platform.getInstallLocation().getURL().getPath() + "dropins" + File.separator + "plugins" + File.separator + "runs" + File.separator).substring(1);
	private static final String ERROR_MESSAGE_X0 = "Error en el Proyecto";
	private static final String ERROR_MESSAGE_X1 = "Error generando el Modelo Prolog";
	private static final String ERROR_MESSAGE_X2 = "Error generando el AST";
	private static final String ERROR_MESSAGE_X3 = "Error corrigiendo el Modelo Prolog";

	private static Teacher TEACHER = null;
	
	private CodeParser codeParser;
	private PrologAnalyzer prologAnalyzer;
	private ModelGenerator modelGenerator;
	private IJavaProject project;
	
	public static Teacher getInstance() {
		if (TEACHER == null)
			TEACHER = new Teacher();
		return TEACHER;
	}

	private Teacher() {
		this.codeParser = new CodeParser();
		this.prologAnalyzer = new PrologAnalyzer();
		this.modelGenerator = new ModelGenerator();
		this.project = null;
	}

	public PrologAnalyzer getAnalyzer() {
		return this.prologAnalyzer;
	}

	public void check(IJavaProject project) {
		this.project = project;
		try {
			ProgressMonitorDialog dialog = new ProgressMonitorDialog(new Shell());
			CorrectionsView view = (CorrectionsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(CorrectionsView.ID);
			CheckRunnable runner = new CheckRunnable(project, codeParser, prologAnalyzer, modelGenerator);
			dialog.run(true, true, runner);

			int result = runner.getResult();
			if (result == 0)
				view.setBugs(runner.getBugs());
			else if (result < 0) {
				String message = null;
				switch (runner.getResult()) {
				case -1:
					message = ERROR_MESSAGE_X0;
					break;
				case -2:
					message = ERROR_MESSAGE_X1;
					break;
				case -3:
					message = ERROR_MESSAGE_X2;
					break;
				case -4:
					message = ERROR_MESSAGE_X3;
					break;
				}
				MessageDialog.openInformation(null, "Ayudante Virtual", message);
			}
			
			this.store();
			new Thread(Synchronizer.getInstance()::sync).start();
		} catch (InvocationTargetException | InterruptedException | PartInitException e) {
			e.printStackTrace();
		}
	}

	private class CheckRunnable implements IRunnableWithProgress {

		private IJavaProject project;
		private CodeParser codeParser;
		private PrologAnalyzer prologAnalyzer;
		private ModelGenerator modelGenerator;
		private List<Bug> bugs;
		private int result;

		public CheckRunnable(IJavaProject project, CodeParser codeParser, PrologAnalyzer prologAnalyzer, ModelGenerator modelGenerator) {
			this.project = project;
			this.codeParser = codeParser;
			this.prologAnalyzer = prologAnalyzer;
			this.modelGenerator = modelGenerator;
			this.bugs = new Vector<Bug>();
		}

		public List<Bug> getBugs() {
			return this.bugs;
		}

		@Override
		public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
			
			try {
				// Obtengo todos los paquetes
				IPackageFragment[] packages = this.project.getPackageFragments();

				// Obtengo solo los paquetes que contengan c�digo
				Vector<IPackageFragment> packages_source = new Vector<IPackageFragment>();
				for (IPackageFragment actual_package : packages)
					if (actual_package.getKind() == IPackageFragmentRoot.K_SOURCE)
						packages_source.add(actual_package);

				this.result = this.codeParser.process(this.project, monitor) * 2;

				if (this.result != 0) // Error o cancelado por el usuario
					return;
			
				this.result = this.modelGenerator.process(this.codeParser.getUnits(), monitor) * 3;

				if (this.result != 0) // Error o cancelado por el usuario
					return;
			
				this.result = this.prologAnalyzer.process(this.modelGenerator.getMapper(), this.modelGenerator.getPrologCode(), this.modelGenerator.getFactory(), monitor) * 4;

				if (this.result != 0) // Error o cancelado por el usuario
					return;

				this.bugs.addAll(this.prologAnalyzer.getBugs());

				monitor.done();
			} catch (CoreException e) {
				monitor.done();
				this.result = -1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public int getResult() {
			return this.result;
		}
		
	}
	
	private Configuration currentConfiguration() {
		Configuration configuration = new Configuration();
		configuration.setVersion(TeachingAssistant.VERSION);
		configuration.setRules(this.prologAnalyzer.rulesVersions());
		configuration.setPredicates(this.prologAnalyzer.predicatesVersions());
		return configuration;
	}
	
	private void store() {
		
		CorrectionData data = new CorrectionData();
		
		// Seteo proyecto
		Project project = new Project();
		project.setName(this.project.getJavaProject().getElementName());
		data.setProject(project);
		
		// Seteo la correccion
		Correction correction = new Correction();
		correction.setRepresentation(this.modelGenerator.toRepresentation());
		correction.setCode(this.codeParser.toSourceCodes());
		correction.setConfiguration(this.currentConfiguration());
		data.setCorrection(correction);
		
		// Seteo los errores
		data.setErrors(this.prologAnalyzer.toErrors());
		
		// Almaceno
		StorageManager storageManager = StorageManager.getInstance();
		storageManager.store(data);
		
	}


}

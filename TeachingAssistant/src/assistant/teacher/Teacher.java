package assistant.teacher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import assistant.analyzer.PrologAnalyzer;
import assistant.analyzer.bugs.Bug;
import assistant.modeler.ModelGenerator;
import assistant.parser.CodeParser;
import assistant.uploader.Uploader;
import assistant.views.CorrectionsView;

public class Teacher {

	public static final String LOG_PATH = (Platform.getInstallLocation().getURL().getPath() + "dropins"
			+ File.separator + "plugins" + File.separator + "runs" + File.separator).substring(1);
	private static final String LOG_CANCELED = System.lineSeparator() + "Ejecución Cancelada por el Usuario"
			+ System.lineSeparator();
	private static final String LOG_ERROR = System.lineSeparator() + "Error:" + System.lineSeparator();
	private static final String ERROR_MESSAGE_X0 = "Error en el Proyecto";
	private static final String ERROR_MESSAGE_X1 = "Error generando el Modelo Prolog";
	private static final String ERROR_MESSAGE_X2 = "Error generando el AST";
	private static final String ERROR_MESSAGE_X3 = "Error corrigiendo el Modelo Prolog";

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
			CorrectionsView view = (CorrectionsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().showView(CorrectionsView.ID);
			Long id = System.currentTimeMillis();
			CheckRunnable runner = new CheckRunnable(project, code_parser, prolog_analyzer, model_generator, id);
			dialog.run(true, true, runner);

			if (zipFolder(project, id)) {
				Thread thread = new Thread(new Uploader());
				thread.start();
			} 
			
			int result = runner.getResult();
			if (result == 0)
				view.setBugs(runner.getBugs());
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
		private long id;

		public CheckRunnable(IJavaProject project, CodeParser code_parser, PrologAnalyzer prolog_analyzer,
				ModelGenerator model_generator, long id) {
			this.project = project;
			this.code_parser = code_parser;
			this.prolog_analyzer = prolog_analyzer;
			this.model_generator = model_generator;
			this.id = id;
			this.bugs = new Vector<Bug>();
		}

		public List<Bug> getBugs() {
			return this.bugs;
		}

		@Override
		public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
			PrintWriter logger = null;
			try {
				File dir = new File(LOG_PATH);
				if (!dir.exists())
					dir.mkdirs();
				
				logger = new PrintWriter(LOG_PATH + id + ".log", "UTF-8");

				logger.println("Plugin v1.0");
				logger.println("Usuario: " + System.getProperty("user.name"));
				logger.println("Maquina: " + InetAddress.getLocalHost().getHostName());
				logger.println("Fecha: " + new Date().toString());
				logger.println();
				
				// Obtengo todos los paquetes
				IPackageFragment[] packages = this.project.getPackageFragments();

				// Obtengo solo los paquetes que contengan código
				Vector<IPackageFragment> packages_source = new Vector<IPackageFragment>();
				for (IPackageFragment actual_package : packages)
					if (actual_package.getKind() == IPackageFragmentRoot.K_SOURCE)
						packages_source.add(actual_package);

				result = this.code_parser.process(this.project, monitor, logger) * 10;

				if (result < 0) { // Error
					logger.close();
					return;
				}

				if (result > 0) { // Cancelado por el usuario
					logger.println(LOG_CANCELED);
					logger.close();
					return;
				}

				result = this.model_generator.process(this.code_parser.getUnits(), monitor, logger) * 20;
								
				if (result < 0) { // Error
					logger.close();
					return;
				}

				if (result > 0) { // Cancelado por el usuario
					logger.println(LOG_CANCELED);
					logger.close();
					return;
				}

				result = this.prolog_analyzer.process(this.model_generator.getMapper(),
						this.model_generator.getCheckCode(), this.model_generator.getFactory(), monitor, logger) * 30;

				if (result < 0) { // Error
					logger.close();
					return;
				}

				if (result > 0) { // Cancelado por el usuario
					logger.println(LOG_CANCELED);
					logger.close();
					return;
				}

				this.bugs.addAll(this.prolog_analyzer.getBugs());

				monitor.done();
			} catch (CoreException e) {
				logger.println(LOG_ERROR);
				logger.println(e.toString());
				monitor.done();
				result = -1;
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (logger != null) {
					logger.close();
				}
			}
		}

		public int getResult() {
			return this.result;
		}

	}

	private boolean zipFolder(IJavaProject project, long id) {
		try {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			File workspaceDirectory = workspace.getRoot().getLocation().toFile();

			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(Paths.get(LOG_PATH + id + ".zip").toFile()));

			for (IPackageFragmentRoot pack : project.getPackageFragmentRoots()) {
				if (pack.getKind() == IPackageFragmentRoot.K_SOURCE) {
					Path src = Paths.get(workspaceDirectory.getAbsolutePath() + pack.getPath().toString());
					Files.walkFileTree(src, new SimpleFileVisitor<Path>() {
						public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
							zos.putNextEntry(new ZipEntry(src.relativize(file).toString()));
							Files.copy(file, zos);
							zos.closeEntry();
							return FileVisitResult.CONTINUE;
						}
					});
				}
			}
			zos.putNextEntry(new ZipEntry("plugin_log.log"));
			Path logpath = new File(LOG_PATH + id + ".log").toPath();
			Files.copy(logpath, zos);
			zos.closeEntry();
			zos.close();
			return true;
		} catch (JavaModelException | IOException e) {
			return false;
		}

	}

}

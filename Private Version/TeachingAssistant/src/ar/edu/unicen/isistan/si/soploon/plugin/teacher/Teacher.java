package ar.edu.unicen.isistan.si.soploon.plugin.teacher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
import java.util.ArrayList;
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
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import ar.edu.unicen.isistan.si.soploon.plugin.Soploon;
import ar.edu.unicen.isistan.si.soploon.plugin.eclipse.views.CorrectionsView;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.analyzer.PrologAnalyzer;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.analyzer.bugs.Bug;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.ModelGenerator;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.parser.CodeParser;
import ar.edu.unicen.isistan.si.soploon.plugin.uploader.Uploader;
import ar.edu.unicen.isistan.si.soploon.server.models.Correction;
import ar.edu.unicen.isistan.si.soploon.server.models.SourceCode;

public class Teacher {

	public static final String LOG_PATH = (Platform.getInstallLocation().getURL().getPath() + "dropins" + File.separator + "plugins" + File.separator + "runs" + File.separator).substring(1);
	private static final String LOG_CANCELED = System.lineSeparator() + "Ejecucion Cancelada por el Usuario" + System.lineSeparator();
	private static final String LOG_ERROR = System.lineSeparator() + "Error:" + System.lineSeparator();
	private static final String ERROR_MESSAGE_X0 = "Error en el Proyecto";
	private static final String ERROR_MESSAGE_X1 = "Error generando el Modelo Prolog";
	private static final String ERROR_MESSAGE_X2 = "Error generando el AST";
	private static final String ERROR_MESSAGE_X3 = "Error corrigiendo el Modelo Prolog";

	private static Teacher TEACHER = null;
	
	private CodeParser codeParser;
	private PrologAnalyzer prologAnalyzer;
	private ModelGenerator modelGenerator;

	public static Teacher getInstance() {
		if (TEACHER == null)
			TEACHER = new Teacher();
		return TEACHER;
	}

	private Teacher() {
		this.codeParser = new CodeParser();
		this.prologAnalyzer = new PrologAnalyzer();
		this.modelGenerator = new ModelGenerator();
	}

	public PrologAnalyzer getAnalyzer() {
		return this.prologAnalyzer;
	}

	public void check(IJavaProject project) {
		try {
			ProgressMonitorDialog dialog = new ProgressMonitorDialog(new Shell());
			CorrectionsView view = (CorrectionsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(CorrectionsView.ID);
			Long id = System.currentTimeMillis();
			CheckRunnable runner = new CheckRunnable(project, codeParser, prologAnalyzer, modelGenerator, id);
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
			
			if (zipFolder(project, id)) {
				Thread thread = new Thread(new Uploader());
				thread.start();
			}
			
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
		private long id;

		public CheckRunnable(IJavaProject project, CodeParser codeParser, PrologAnalyzer prologAnalyzer, ModelGenerator modelGenerator, long id) {
			this.project = project;
			this.codeParser = codeParser;
			this.prologAnalyzer = prologAnalyzer;
			this.modelGenerator = modelGenerator;
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

				logger.println();
				logger.println("Usuario: " + System.getProperty("user.name"));
				logger.println("Maquina: " + InetAddress.getLocalHost().getHostName());
				logger.println("Fecha: " + new Date().toString());
				logger.println();

				// Obtengo todos los paquetes
				IPackageFragment[] packages = this.project.getPackageFragments();

				// Obtengo solo los paquetes que contengan c�digo
				Vector<IPackageFragment> packages_source = new Vector<IPackageFragment>();
				for (IPackageFragment actual_package : packages)
					if (actual_package.getKind() == IPackageFragmentRoot.K_SOURCE)
						packages_source.add(actual_package);

				result = this.codeParser.process(this.project, monitor) * 2;

				if (result < 0) { // Error
					logger.close();
					return;
				}

				if (result > 0) { // Cancelado por el usuario
					logger.println(LOG_CANCELED);
					logger.close();
					return;
				}

				result = this.modelGenerator.process(this.codeParser.getUnits(), monitor, logger) * 3;

				if (result < 0) { // Error
					logger.close();
					return;
				}

				if (result > 0) { // Cancelado por el usuario
					logger.println(LOG_CANCELED);
					logger.close();
					return;
				}

				result = this.prologAnalyzer.process(this.modelGenerator.getMapper(), this.modelGenerator.getCheckCode(), this.modelGenerator.getFactory(), monitor) * 4;

				if (result < 0) { // Error
					logger.close();
					return;
				}

				if (result > 0) { // Cancelado por el usuario
					logger.println(LOG_CANCELED);
					logger.close();
					return;
				}

				this.bugs.addAll(this.prologAnalyzer.getBugs());

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

	private boolean store() {
				
		Correction correction = new Correction();
		correction.setDate(System.currentTimeMillis());
		correction.setVersionSoploon(Soploon.VERSION);
		correction.setRepresentation(new ArrayList<String>(this.modelGenerator.getCheckCode().getFacts()));
		
		ArrayList<SourceCode> sourceCodes = new ArrayList<SourceCode>();
		for (CompilationUnit cunit: this.codeParser.getUnits()) {
			SourceCode sourceCode = new SourceCode();
			sourceCode.setPath(cunit.getJavaElement().getPath().toString());
			File file = cunit.getJavaElement().getPath().toFile();
			try (FileReader fileReader = new FileReader(file); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
				StringBuilder builder = new StringBuilder();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					builder.append(line);
				}
				String code = builder.toString();
				sourceCode.setCode(code);
				sourceCodes.add(sourceCode);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		correction.setCode(sourceCodes);
		// ID Usuario
		// ID Proyecto

		// ArrayList<SourceCode> Código fuente
		
		return false;
	}
}

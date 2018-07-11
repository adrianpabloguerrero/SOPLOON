package assistant.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import assistant.teacher.Teacher;

public class CheckHandler extends AbstractHandler {

	private static final String ERROR_NO_PROJECT = "Selecciona un proyecto Java antes de pedir correcciones";
	private static final String ERROR_CLOSED = "El proyecto seleccionado debe estar abierto";
	private static final String ERROR_COMPILATION = "El proyecto no debe contener errores de compilaci�n";
	private static final String ERROR_NO_RULES = "Es necesario actualizar para corregir el proyecto";

	private Teacher teacher;
	private IJavaProject last_check;
	
	public CheckHandler() {
		this.teacher = Teacher.getInstance();
		this.last_check = null;
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection("org.eclipse.jdt.ui.PackageExplorer");
					
		if (selection == null)
			selection = (IStructuredSelection) window.getSelectionService().getSelection("org.eclipse.ui.navigator.ProjectExplorer");
		
		Object object = null;
		if (selection != null)
			object = selection.getFirstElement();
		
		IJavaProject project = null;
		if (IJavaProject.class.isInstance(object))
			project = ((IJavaProject) object);
		else if (IProject.class.isInstance(object)) {
			project = JavaCore.create((IProject) object);
		}
		
		if (project == null && this.last_check != null)
			project = this.last_check;
		
		if (project != null && project.exists() && !hasCompilationErrors(project)) {
			this.teacher.init();
			if (this.teacher.getAnalyzer().getRuleSet() != null) {
				this.last_check = project;
				this.teacher.check(project);
			} else {
				MessageDialog.openInformation(null, "Ayudante Virtual", ERROR_NO_RULES);
			}
		}
		else if (project == null) 
			MessageDialog.openInformation(null, "Ayudante Virtual", ERROR_NO_PROJECT);
		else if (!project.exists())
			MessageDialog.openInformation(null, "Ayudante Virtual", ERROR_CLOSED);
		else if (hasCompilationErrors(project))
			MessageDialog.openInformation(null, "Ayudante Virtual", ERROR_COMPILATION);
		
		return null;
	}

	private boolean hasCompilationErrors(IJavaProject javaProject) {
		try {
			IMarker[] problems = javaProject.getProject().findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);

			for (int problemsIndex = 0; problemsIndex < problems.length; problemsIndex++) {
				if (IMarker.SEVERITY_ERROR == problems[problemsIndex].getAttribute(IMarker.SEVERITY,
						IMarker.SEVERITY_INFO))
					return true;
			}

		} catch (CoreException e) {
			return true;
		}

		return false;
	}
}

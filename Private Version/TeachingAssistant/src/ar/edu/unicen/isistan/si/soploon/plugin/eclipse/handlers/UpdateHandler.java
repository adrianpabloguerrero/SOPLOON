package ar.edu.unicen.isistan.si.soploon.plugin.eclipse.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.dialogs.MessageDialog;
import ar.edu.unicen.isistan.si.soploon.plugin.updater.UpdateTask;

public class UpdateHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) {	
		UpdateTask updateTask = new UpdateTask();
		updateTask.run();
		
		int result = updateTask.getResult();
		
		String message = null;
		switch (result) {
			case UpdateTask.ERROR_CONNECTIVITY:
				message = "[WARN] Hay problemas de conectividad con el servidor";
				break;
			case UpdateTask.ERROR_STORING:
				message = "[ERROR] Error al almacenar las reglas nuevas";
				break;
			case UpdateTask.NOT_UPDATED:
				message = "[OK] No es necesario actualizar";
				break;
			case UpdateTask.UPDATED:
				message = "[OK] Se actualizaron los errores";
				break;
		}
		
		MessageDialog.openInformation(null, "Ayudante Virtual", message);

		return null;
	}

}

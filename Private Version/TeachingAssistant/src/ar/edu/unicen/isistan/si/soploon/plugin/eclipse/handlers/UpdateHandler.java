package ar.edu.unicen.isistan.si.soploon.plugin.eclipse.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.dialogs.MessageDialog;

import ar.edu.unicen.isistan.si.soploon.plugin.api.Updater;

public class UpdateHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) {	
		int result = Updater.getInstance().update();
		
		String message = null;
		switch (result) {
			case Updater.ERROR_CONNECTIVITY:
				message = "[WARN] Hay problemas de conectividad con el servidor";
				break;
			case Updater.ERROR_STORING:
				message = "[ERROR] Error al almacenar las reglas nuevas";
				break;
			case Updater.NOT_UPDATED:
				message = "[OK] No es necesario actualizar";
				break;
			case Updater.UPDATED:
				message = "[OK] Se actualizaron los errores";
				break;
		}
		
		MessageDialog.openInformation(null, "Ayudante Virtual", message);

		return null;
	}

}

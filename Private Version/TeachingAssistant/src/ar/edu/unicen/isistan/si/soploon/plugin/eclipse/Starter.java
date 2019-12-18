package ar.edu.unicen.isistan.si.soploon.plugin.eclipse;

import org.eclipse.ui.IStartup;

import ar.edu.unicen.isistan.si.soploon.plugin.storage.StorageManager;
import ar.edu.unicen.isistan.si.soploon.plugin.updater.UpdateTask;

public class Starter implements IStartup {

	@Override
	public void earlyStartup() {
		StorageManager storeManager = StorageManager.getInstance();

		Thread thread = new Thread(new UpdateTask());
		thread.start();
		
		if (!storeManager.getUserData().hasUserId() /* si hay archivos pendientes de subir*/) {
			// TODO Aca largar un thread con el uploader
		}
	}

}

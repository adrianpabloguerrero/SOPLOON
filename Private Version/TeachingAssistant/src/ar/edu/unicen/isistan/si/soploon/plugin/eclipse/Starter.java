package ar.edu.unicen.isistan.si.soploon.plugin.eclipse;

import org.eclipse.ui.IStartup;

import ar.edu.unicen.isistan.si.soploon.plugin.api.SyncTask;
import ar.edu.unicen.isistan.si.soploon.plugin.storage.StorageManager;

public class Starter implements IStartup {

	@Override
	public void earlyStartup() {
		StorageManager storeManager = StorageManager.getInstance();

		if (!storeManager.getData().hasUser() || !storeManager.getData().getPendingCorrections().isEmpty()) {
			Thread thread = new Thread(new SyncTask());
			thread.start();
		}
	}

}

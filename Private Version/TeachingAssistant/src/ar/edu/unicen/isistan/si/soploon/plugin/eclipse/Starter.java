package ar.edu.unicen.isistan.si.soploon.plugin.eclipse;

import org.eclipse.ui.IStartup;

import ar.edu.unicen.isistan.si.soploon.plugin.api.SyncTask;
import ar.edu.unicen.isistan.si.soploon.plugin.api.UpdateTask;

public class Starter implements IStartup {

	@Override
	public void earlyStartup() {
		new Thread(new UpdateTask()).start();
		new Thread(new SyncTask()).start();
	}

}

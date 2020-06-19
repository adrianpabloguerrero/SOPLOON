package ar.edu.unicen.isistan.si.soploon.plugin.eclipse;

import org.eclipse.ui.IStartup;

import ar.edu.unicen.isistan.si.soploon.plugin.api.Synchronizer;
import ar.edu.unicen.isistan.si.soploon.plugin.api.Updater;

public class Starter implements IStartup {

	@Override
	public void earlyStartup() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Synchronizer.getInstance().sync();
				Updater.getInstance().update();
			}
		}).start();
	}

}

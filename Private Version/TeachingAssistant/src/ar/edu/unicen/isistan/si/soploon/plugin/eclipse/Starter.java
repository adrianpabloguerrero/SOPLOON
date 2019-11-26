package ar.edu.unicen.isistan.si.soploon.plugin.eclipse;

import org.eclipse.ui.IStartup;

import ar.edu.unicen.isistan.si.soploon.plugin.storage.StorageManager;

public class Starter implements IStartup {

	@Override
	public void earlyStartup() {
		StorageManager sm = StorageManager.getInstance();
		
		System.out.println(sm.getUserId());
		sm.setUserId(System.currentTimeMillis());
		System.out.println(sm.getUserId());
	}

}

package ar.edu.unicen.isistan.si.soploon.plugin.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.eclipse.core.runtime.Platform;

public class StorageManager {

	private static final String USER_ID = "userid";
	private static final String STORAGE_PATH = (Platform.getInstallLocation().getURL().getPath() + "dropins" + File.separator + "plugins" + File.separator + "ar.edu.unicen.isistan.si.soploon" + File.separator).substring(1);
	private static final String CONFIG_PATH = STORAGE_PATH + "soploon.config";

	private static StorageManager INSTANCE;
	
	public static StorageManager getInstance() {
		if (INSTANCE == null)
			INSTANCE = new StorageManager();
		return INSTANCE;
	}
	
	private Properties properties;

	private StorageManager() {
		System.out.println(STORAGE_PATH);
		this.properties = new Properties();
		this.init();
	}

	private boolean saveConfig() {
		File file = new File(CONFIG_PATH);
		try (FileOutputStream output = new FileOutputStream(file)) {
			this.properties.store(output, null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private void init() {
		File folder = new File(STORAGE_PATH);
		if (!folder.exists())
			folder.mkdirs();
		File file = new File(CONFIG_PATH);
		if (file.exists()) {
			try (FileInputStream input = new FileInputStream(file)) {
				this.properties.load(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public long getUserId() {
		return (long) this.properties.getOrDefault(USER_ID, -1L);
	}
	
	public void setUserId(long userid) {
		this.properties.put(USER_ID, userid);
		this.saveConfig();
	}

}

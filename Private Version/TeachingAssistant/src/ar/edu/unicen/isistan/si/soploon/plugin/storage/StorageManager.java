package ar.edu.unicen.isistan.si.soploon.plugin.storage;

import java.io.File;
import org.eclipse.core.runtime.Platform;

public class StorageManager {

	private static final String STORAGE_PATH = (Platform.getInstallLocation().getURL().getPath() + "dropins" + File.separator + "plugins" + File.separator + "ar.edu.unicen.isistan.si.soploon" + File.separator).substring(1);
	private static final String DATA_PATH = STORAGE_PATH + "data.json";
	private static final String CONFIG_PATH = STORAGE_PATH + "config.json";

	private static StorageManager INSTANCE;
	
	private UserData userData;
	private ConfigurationData configurationData;
	
	public static StorageManager getInstance() {
		if (INSTANCE == null)
			INSTANCE = new StorageManager();
		return INSTANCE;
	}
	
	private StorageManager() {
		this.init();
	}

	private void init() {
		File folder = new File(STORAGE_PATH);
		if (!folder.exists())
			folder.mkdirs();
		JsonFile<UserData> dataFile = new JsonFile<UserData>(DATA_PATH);
		this.userData = dataFile.read(UserData.class);
		if (this.userData == null) {
			this.userData = new UserData();
			dataFile.store(this.userData);
		}
		JsonFile<ConfigurationData> configFile = new JsonFile<ConfigurationData>(CONFIG_PATH);
		this.configurationData = configFile.read(ConfigurationData.class);
		if (this.configurationData == null) {
			this.configurationData = new ConfigurationData();
			configFile.store(this.configurationData);
		}
	}

	public UserData getUserData() {
		return userData;
	}

	public ConfigurationData getConfigurationData() {
		return this.configurationData;
	}
	
	public boolean storeUserData(UserData userData) {
		this.userData = userData;
		JsonFile<UserData> file = new JsonFile<UserData>(DATA_PATH);
		return file.store(userData);
	}

	public synchronized boolean storeConfigurationData(ConfigurationData configurationData) {
		this.configurationData = configurationData;
		JsonFile<ConfigurationData> file = new JsonFile<ConfigurationData>(CONFIG_PATH);
		return file.store(configurationData);
	}
	
}

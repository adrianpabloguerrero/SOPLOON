package ar.edu.unicen.isistan.si.soploon.plugin.storage;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.core.runtime.Platform;

public class StorageManager {

	private static final String STORAGE_PATH = (Platform.getInstallLocation().getURL().getPath() + "dropins" + File.separator + "plugins" + File.separator + "ar.edu.unicen.isistan.si.soploon" + File.separator).substring(1);
	private static final String CORRECTIONS_PATH = STORAGE_PATH + "corrections" + File.separator;
	private static final String JSON_EXT = ".json";
	private static final String DATA_PATH = STORAGE_PATH + "data" + JSON_EXT;
	private static final String CONFIG_PATH = STORAGE_PATH + "config" + JSON_EXT;
	
	private static StorageManager INSTANCE;
	
	private Configuration configuration;
	private Data data;
	
	public synchronized static StorageManager getInstance() {
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
		File correctionsFolder = new File(CORRECTIONS_PATH);
		if (!correctionsFolder.exists())
			correctionsFolder.mkdirs();
		
		JsonFile<Data> dataFile = new JsonFile<Data>(DATA_PATH);
		this.data = dataFile.read(Data.class);
		if (this.data == null) {
			this.data = new Data();
			dataFile.store(this.data);
		}
		
		JsonFile<Configuration> configFile = new JsonFile<Configuration>(CONFIG_PATH);
		this.configuration = configFile.read(Configuration.class);
		if (this.configuration == null) {
			this.configuration = new Configuration();
			configFile.store(this.configuration);
		}
	}

	public Data getData() {
		return data;
	}

	public Configuration getConfiguration() {
		return this.configuration;
	}
	
	public synchronized boolean store(Data data) {
		this.data = data;
		JsonFile<Data> file = new JsonFile<Data>(DATA_PATH);
		return file.store(data);
	}

	public synchronized boolean storeConfiguration(Configuration configuration) {
		this.configuration = configuration;
		JsonFile<Configuration> file = new JsonFile<Configuration>(CONFIG_PATH);
		return file.store(configuration);
	}

	public synchronized void store(CorrectionData correctionData) {
		this.populate(correctionData);
		JsonFile<CorrectionData> correctionFile = new JsonFile<CorrectionData>(CORRECTIONS_PATH + correctionData.getCorrection().getDate() + JSON_EXT);
		correctionFile.store(correctionData);		
	}
	
	public synchronized CorrectionData getCorrection(long id) {
		JsonFile<CorrectionData> dataFile = new JsonFile<CorrectionData>(CORRECTIONS_PATH + id + JSON_EXT);
		CorrectionData correctionData = dataFile.read(CorrectionData.class);
		if (correctionData == null)
			return null;
		this.populate(correctionData);		
		return correctionData;
	}
	
	public synchronized void deleteCorrection(long correctionId) {
		File file = new File(CORRECTIONS_PATH + correctionId + JSON_EXT);
		if (file.exists())
			file.delete();
	}
	
	private void populate(CorrectionData correctionData) {
		if (correctionData.getDate() == 0)
			correctionData.setDate(System.currentTimeMillis()/1000);
		correctionData.setUserId(this.data.getUserId());
		Integer projectId = this.data.getProjectId(correctionData.getProject());
		if (projectId != null)
			correctionData.setProjectId(projectId);	
	}

	public ArrayList<Integer> pendingCorrections() {
		ArrayList<Integer> pendings = new ArrayList<Integer>();
		File correctionsFolder = new File(CORRECTIONS_PATH);
		for (File file: correctionsFolder.listFiles()) {
			try {
				pendings.add(Integer.valueOf(file.getName().split("\\.")[0]));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pendings;
	}
	
}

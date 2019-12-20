package ar.edu.unicen.isistan.si.soploon.plugin.api;

import java.util.List;

import ar.edu.unicen.isistan.si.soploon.plugin.Soploon;
import ar.edu.unicen.isistan.si.soploon.plugin.storage.Configuration;
import ar.edu.unicen.isistan.si.soploon.plugin.storage.StorageManager;
import ar.edu.unicen.isistan.si.soploon.server.client.SoploonClient;
import ar.edu.unicen.isistan.si.soploon.server.models.Predicate;
import ar.edu.unicen.isistan.si.soploon.server.models.Rule;

public class Updater {

	private static Updater INSTANCE = null;

	public static final int NOT_FINISHED = 0;
	public static final int ERROR_CONNECTIVITY = -2;
	public static final int ERROR_STORING = -3;
	public static final int UPDATED = 1;
	public static final int NOT_UPDATED = 2;
	
	private SoploonClient client;
	private StorageManager storageManager;

	private Updater() {
		this.client = new SoploonClient(Soploon.BASE_HOST);
		this.storageManager = StorageManager.getInstance();
	}
	
	public synchronized static Updater getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Updater();
		return INSTANCE;
	}

	public synchronized int update() {
		
		List<Rule> rules = this.client.getRules();
		List<Predicate> predicates = this.client.getPredicates();

		if (rules != null && predicates != null) {
			Configuration configuration = new Configuration();
			configuration.setRules(rules);
			configuration.setPredicates(predicates);
			
			Configuration currentConfiguration = this.storageManager.getConfiguration();
			if (!configuration.equals(currentConfiguration))
				return (this.storageManager.storeConfiguration(configuration)) ? UPDATED : ERROR_STORING;
			else
				return NOT_UPDATED;
		} else {
			return ERROR_CONNECTIVITY;
		}
	}
	
}

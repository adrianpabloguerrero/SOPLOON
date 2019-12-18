package ar.edu.unicen.isistan.si.soploon.plugin.updater;

import java.util.List;

import ar.edu.unicen.isistan.si.soploon.plugin.storage.ConfigurationData;
import ar.edu.unicen.isistan.si.soploon.plugin.storage.StorageManager;
import ar.edu.unicen.isistan.si.soploon.server.client.SoploonClient;
import ar.edu.unicen.isistan.si.soploon.server.models.Predicate;
import ar.edu.unicen.isistan.si.soploon.server.models.Rule;

public class UpdateTask implements Runnable {

	public static final int NOT_FINISHED = 0;
	public static final int ERROR_CONNECTIVITY = -2;
	public static final int ERROR_STORING = -3;
	public static final int UPDATED = 1;
	public static final int NOT_UPDATED = 2;
	
	private int result;
	
	public UpdateTask() {
		this.result = NOT_FINISHED;
	}
	
	@Override
	public void run() {
		
		SoploonClient client = new SoploonClient("http://localhost:8080/soploon/api");
		
		List<Rule> rules = client.getRules();
		List<Predicate> predicates = client.getPredicates();

		if (rules != null && predicates != null) {
			ConfigurationData configuration = new ConfigurationData();
			configuration.setRules(rules);
			configuration.setPredicates(predicates);
			
			StorageManager manager = StorageManager.getInstance();
			ConfigurationData currentConfiguration = manager.getConfigurationData();
			if (!configuration.equals(currentConfiguration))
				result = (manager.storeConfigurationData(configuration)) ? UPDATED : ERROR_STORING;
			else
				result = NOT_UPDATED;
		} else {
			result = ERROR_CONNECTIVITY;
		}
	}
	
	public int getResult() {
		return this.result;
	}

}

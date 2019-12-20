package ar.edu.unicen.isistan.si.soploon.plugin.api;

import java.util.List;

import ar.edu.unicen.isistan.si.soploon.plugin.Soploon;
import ar.edu.unicen.isistan.si.soploon.plugin.storage.Configuration;
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
		
		SoploonClient client = new SoploonClient(Soploon.BASE_HOST);
		List<Rule> rules = client.getRules();
		List<Predicate> predicates = client.getPredicates();

		if (rules != null && predicates != null) {
			Configuration configuration = new Configuration();
			configuration.setRules(rules);
			configuration.setPredicates(predicates);
			
			StorageManager manager = StorageManager.getInstance();
			Configuration currentConfiguration = manager.getConfiguration();
			if (!configuration.equals(currentConfiguration))
				this.result = (manager.storeConfiguration(configuration)) ? UPDATED : ERROR_STORING;
			else
				this.result = NOT_UPDATED;
		} else {
			this.result = ERROR_CONNECTIVITY;
		}
	}
	
	public int getResult() {
		return this.result;
	}

}

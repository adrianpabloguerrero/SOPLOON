package ar.edu.unicen.isistan.si.soploon.plugin.api;

import java.util.ArrayList;
import java.util.UUID;

import ar.edu.unicen.isistan.si.soploon.plugin.Soploon;
import ar.edu.unicen.isistan.si.soploon.plugin.storage.CorrectionData;
import ar.edu.unicen.isistan.si.soploon.plugin.storage.Data;
import ar.edu.unicen.isistan.si.soploon.plugin.storage.StorageManager;
import ar.edu.unicen.isistan.si.soploon.server.client.SoploonClient;
import ar.edu.unicen.isistan.si.soploon.server.models.Correction;
import ar.edu.unicen.isistan.si.soploon.server.models.Error;
import ar.edu.unicen.isistan.si.soploon.server.models.Project;
import ar.edu.unicen.isistan.si.soploon.server.models.User;
import ar.edu.unicen.isistan.si.soploon.server.models.User.Role;

public class Synchronizer {

	private static Synchronizer INSTANCE = null;

	private SoploonClient client;
	private StorageManager storageManager;
	
	private Synchronizer() {
		this.storageManager = StorageManager.getInstance();
		User user = this.storageManager.getData().getUser();
		if (user != null)
			this.client = new SoploonClient(Soploon.BASE_HOST, user);
		else
			this.client = new SoploonClient(Soploon.BASE_HOST);
	}
	
	public synchronized static Synchronizer getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Synchronizer();
		return INSTANCE;
	}

	public synchronized void sync() {
		
		if (!this.registerUserIfNeeded())
			return;

		this.client.authenticate();
		
		ArrayList<Long> pending = this.storageManager.pendingCorrections();

		for (long correctionId : pending) {
			CorrectionData correctionData = this.storageManager.getCorrection(correctionId);

			if (!this.postProjectIfNeeded(correctionData))
				return;

			if (!this.postCorrectionIfNeeded(correctionData))
				return;

			if (!this.postErrorsIfNeeded(correctionData))
				return;

			this.storageManager.deleteCorrection(correctionId);
		}

	}

	private boolean registerUserIfNeeded() {

		if (this.storageManager.getData().hasUser())
			return true;

		User user = new User();
		user.setCreationDate(System.currentTimeMillis());
		String time = String.valueOf(user.getCreationDate());
		user.setName(System.getProperty("user.name") + "-" + time.substring(time.length()-3));
		user.setPassword(UUID.randomUUID().toString());
		user.setRole(Role.student);
		User newUser = client.postUser(user);
		
		if (newUser == null) {
			return false;
		}

		user.setId(newUser.getId());
		Data data = this.storageManager.getData();
		data.setUser(user);

		if (!this.storageManager.store(data))
			return false;

		this.client = new SoploonClient(Soploon.BASE_HOST, user);

		return true;
	}

	private boolean postProjectIfNeeded(CorrectionData correctionData) {

		Project project = correctionData.getProject();

		if (project.getId() != 0)
			return true;

		Project result = this.client.postProject(project);
		if (result != null) {
			correctionData.setProjectId(result.getId());
			Data data = this.storageManager.getData();
			data.addProject(project.getName(), result.getId());
			this.storageManager.store(data);
			return true;
		}

		return false;

	}

	private boolean postCorrectionIfNeeded(CorrectionData correctionData) {

		Correction correction = correctionData.getCorrection();
	
		Correction result = this.client.postCorrection(correction);
		if (result != null) {
			correctionData.setDate(result.getDate());
			return true;
		}

		return false;
	}

	private boolean postErrorsIfNeeded(CorrectionData correctionData) {
		ArrayList<Error> errors = correctionData.getErrors();
		ArrayList<Error> result = this.client.postErrors(errors);
		if (result != null) {
			return true;
		} else {
			return false;
		}
	}

}

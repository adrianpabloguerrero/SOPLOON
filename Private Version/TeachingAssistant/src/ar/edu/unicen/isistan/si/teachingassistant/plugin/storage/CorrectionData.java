package ar.edu.unicen.isistan.si.teachingassistant.plugin.storage;

import java.util.ArrayList;

import ar.edu.unicen.isistan.si.soploon.server.models.Correction;
import ar.edu.unicen.isistan.si.soploon.server.models.Error;
import ar.edu.unicen.isistan.si.soploon.server.models.Project;

public class CorrectionData {

	private Project project;
	private Correction correction;
	private ArrayList<Error> errors;

	public Correction getCorrection() {
		return correction;
	}

	public void setCorrection(Correction correction) {
		this.correction = correction;
	}

	public ArrayList<Error> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<Error> errors) {
		this.errors = errors;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setUserId(long userId) {
		this.project.setUserId(userId);
		this.correction.setUserId(userId);
		for (Error error: this.errors)
			error.setUserId(userId);
	}
	
	public void setProjectId(long projectId) {
		this.project.setId(projectId);
		this.correction.setProjectId(projectId);
		for (Error error: this.errors)
			error.setProjectId(projectId);
	}
	
	public void setDate(long date) {
		this.correction.setDate(date);
		for (Error error: this.errors)
			error.setDate(date);
	}

	public long getDate() {
		return this.correction.getDate();
	}
	
	public long getProjectId() {
		return this.project.getId();
	}
	
	public long getUserId() {
		return this.project.getUserId();
	}
	
}

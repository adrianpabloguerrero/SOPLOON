package ar.edu.unicen.isistan.si.soploon.server.models;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

public class Error {

	@Expose
	private int id;
	@Expose
	private int projectId;
	@Expose
	private int userId;
	@Expose
	private long date;
	@Expose
	private int ruleId;
	@Expose
	private int versionRule;
	@Expose
	private ArrayList<CodeLocation> codeLocation;
	@Expose
	private ArrayList<Integer> representationLocation;
	@Expose
	private int reviewed;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public int getVersionRule() {
		return versionRule;
	}

	public void setVersionRule(int versionRule) {
		this.versionRule = versionRule;
	}

	public ArrayList<CodeLocation> getCodeLocation() {
		return codeLocation;
	}

	public void setCodeLocation(ArrayList<CodeLocation> codeLocation) {
		this.codeLocation = codeLocation;
	}

	public ArrayList<Integer> getRepresentationLocation() {
		return representationLocation;
	}

	public void setRepresentationLocation(ArrayList<Integer> representationLocation) {
		this.representationLocation = representationLocation;
	}

	public int getReviewed() {
		return reviewed;
	}

	public void setReviewed(int reviewed) {
		this.reviewed = reviewed;
	}

	@Override
	public String toString() {
		return "Error [id=" + id + ", projectId=" + projectId + ", userId=" + userId + ", date=" + date + ", ruleId="
				+ ruleId + ", versionRule=" + versionRule + ", codeLocation=" + codeLocation
				+ ", representationLocation=" + representationLocation + ", reviewed=" + reviewed + "]";
	}

}

package ar.edu.unicen.isistan.si.soploon.server.models;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

public class Stats {
	
	@Expose
	private int usersQuantity;
	@Expose
	private int errorsQuantity;
	@Expose
	private int correctionsQuantity;
	@Expose
	private int projectsQuantity;
	@Expose
	private ArrayList <ErrorStatsElement> errorsRateElement;
	@Expose 
	private ArrayList <ErrorStatsElement> errorsTopFive;
	@Expose
	private ArrayList <ErrorStatsElement> acumCorrections;
	@Expose
	private LastUse lastUse;
	

	public int getUsersQuantity() {
		return usersQuantity;
	}
	public void setUsersQuantity(int usersQuantity) {
		this.usersQuantity = usersQuantity;
	}
	public int getErrorsQuantity() {
		return errorsQuantity;
	}
	public void setErrorsQuantity(int errorsQuantity) {
		this.errorsQuantity = errorsQuantity;
	}
	public int getCorrectionsQuantity() {
		return correctionsQuantity;
	}
	public void setCorrectionsQuantity(int correctionsQuantity) {
		this.correctionsQuantity = correctionsQuantity;
	}
	public int getProjectsQuantity() {
		return projectsQuantity;
	}
	public void setProjectsQuantity(int projectsQuantity) {
		this.projectsQuantity = projectsQuantity;
	}
	public ArrayList<ErrorStatsElement> getErrorsRateElement() {
		return errorsRateElement;
	}
	public void setErrorsRateElement(ArrayList<ErrorStatsElement> errorsRateElement) {
		this.errorsRateElement = errorsRateElement;
	}
	public ArrayList<ErrorStatsElement> getErrorsTopFive() {
		return errorsTopFive;
	}
	public void setErrorsTopFive(ArrayList<ErrorStatsElement> errorsTopFive) {
		this.errorsTopFive = errorsTopFive;
	}
	public ArrayList<ErrorStatsElement> getAcumCorrections() {
		return acumCorrections;
	}
	public void setAcumCorrections(ArrayList<ErrorStatsElement> acumCorrections) {
		this.acumCorrections = acumCorrections;
	}
	public LastUse getLastUse() {
		return lastUse;
	}
	public void setLastUse(LastUse lastUse) {
		this.lastUse = lastUse;
	}
	
}

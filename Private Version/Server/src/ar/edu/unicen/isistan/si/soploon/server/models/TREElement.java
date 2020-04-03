package ar.edu.unicen.isistan.si.soploon.server.models;

import com.google.gson.annotations.Expose;

public class TREElement {
	
	@Expose
	private float value;
	@Expose
	private int minErrors;
	@Expose
	private int maxElement;
	
	
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public int getMinErrors() {
		return minErrors;
	}
	public void setMinErrors(int minErrors) {
		this.minErrors = minErrors;
	}
	public int getMaxElement() {
		return maxElement;
	}
	public void setMaxElement(int maxElement) {
		this.maxElement = maxElement;
	}
	
}

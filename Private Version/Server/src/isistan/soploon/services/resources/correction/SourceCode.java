package isistan.soploon.services.resources.correction;

import com.google.gson.annotations.Expose;

public class SourceCode {
	
	@Expose
	private String path;
	@Expose
	private String code;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}

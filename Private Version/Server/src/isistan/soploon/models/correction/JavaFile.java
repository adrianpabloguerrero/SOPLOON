package isistan.soploon.models.correction;

import com.google.gson.annotations.Expose;

public class JavaFile {

	@Expose
	private String path;
	@Expose
	private String code;

	public JavaFile() {

	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}

package isistan.soploon.models.predicate;

public class Predicate {

	private int id;
	private int version;
	private String name;
	private String description;
	private String code;
	
	public Predicate (){
		this.id = 0;
		this.version = 1;
		this.name = null;
		this.description = null;
		this.code = null;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}

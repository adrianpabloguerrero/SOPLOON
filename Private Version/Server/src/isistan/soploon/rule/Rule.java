package isistan.soploon.rule;

public class Rule {

	private int id;
	private String name;
	private String description;
	private String link;
	private String query;
	private String predicate;
	private Boolean activated;
	private String version;

	public Rule () {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getPredicate() {
		return this.predicate;
	}
	
	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}
	
	public Boolean getActivated() {
		return this.activated;
	}
	
	public void setActivated(Boolean activated) {
		this.activated = activated;
	}
	
	public String getVersion() {
		return this.version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
}

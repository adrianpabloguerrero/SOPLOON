package isistan.soploon.models.rule;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Prolog;
import alice.tuprolog.Theory;

public class Rule {

	private int id;
	private int version;
	private String name;
	private String description;
	private String link;
	private String query;
	private String predicate;
	private boolean activated;

	public Rule() {
		this.id = 0;
		this.version = 1;
		this.name = null;
		this.description = null;
		this.link = null;
		this.query = null;
		this.predicate = null;
		this.activated = false;
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

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public boolean isValid() {
		return  this.id >= 0 &&
				this.version >= 0 && 
				this.name != null && 
				this.description != null && 
				this.link != null
				&& this.query != null && 
				this.predicate != null && 
				this.isPrologValid();
	}

	private boolean isPrologValid() {
		try {
			Prolog engine = new Prolog();
			engine.addTheory(new Theory(this.query));
			engine.addTheory(new Theory(this.predicate));
			return true;
		} catch (InvalidTheoryException e) {
			return false;
		}
	}

}

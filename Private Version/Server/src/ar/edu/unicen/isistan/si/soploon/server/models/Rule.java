package ar.edu.unicen.isistan.si.soploon.server.models;

import com.google.gson.annotations.Expose;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Prolog;
import alice.tuprolog.Theory;

public class Rule {
	
	@Expose
	private int id;
	@Expose
	private int version;
	@Expose
	private String name;
	@Expose
	private String description;
	@Expose
	private String link;
	@Expose
	private String query;
	@Expose
	private String code;
	@Expose
	private boolean activated;

	public Rule() {
		this.id = 0;
		this.version = 1;
		this.name = null;
		this.description = null;
		this.link = null;
		this.query = null;
		this.code = null;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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
				this.code != null && 
				this.isPrologValid();
	}

	private boolean isPrologValid() {
		try {
			Prolog engine = new Prolog();
			engine.addTheory(new Theory(this.query));
			engine.addTheory(new Theory(this.code));
			return true;
		} catch (InvalidTheoryException e) {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Rule [id=" + id + ", version=" + version + ", name=" + name + ", description=" + description + ", link="
				+ link + ", query=" + query + ", code=" + code + ", activated=" + activated + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (activated ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if (activated != other.activated)
			return false;
		if (id != other.id)
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}

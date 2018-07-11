package assistant.analyzer;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("simple-predicate")
public class Predicate {

	private String title;
	private String description;
	private String predicates;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPredicates() {
		return predicates;
	}

	public void setPredicates(String predicates) {
		this.predicates = predicates;
	}

}

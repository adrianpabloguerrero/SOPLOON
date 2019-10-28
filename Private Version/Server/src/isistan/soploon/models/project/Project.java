package isistan.soploon.models.project;


public class Project {
	
	private int userId;
	private int id;
	private String name;


	public Project () {

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

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getUserId() {
		return userId;
	}
	
	public void setIdUser(int userId) {
		this.userId = userId;
	}






}

package isistan.soploon.services.resources;

import isistan.soploon.database.Database;
import isistan.soploon.models.error.ErrorDao;

public class ErrorResource {
	
	private Database database;
	private ErrorDao dao;

	public ErrorResource (Database database) {
		this.database = database;
		this.dao = new ErrorDao(this.database);
	}

}




package isistan.soploon.models.error;

import isistan.soploon.database.Database;

public class ErrorDao {

	
	private static final String TABLE_NAME = "soploon.error";
	private static final String COLUMNS_INSERT = "(user_id,project_id,date,id_rule,version_rule,code_location,representation_location)";
	private static final String VALUES = "(?,?,?,?,?,?,?)";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS_INSERT + " VALUES";
	private static final String SINGLE_INSERT= INSERT+ " " + VALUES + ";";
	private static final String CONDITION_ID = " WHERE id = ? ";
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " " + CONDITION_ID + ";";
	
	
	private Database database;
	
	public ErrorDao (Database database) {
		this.database = database;
	}
	
	
	
	
	
	
	
}

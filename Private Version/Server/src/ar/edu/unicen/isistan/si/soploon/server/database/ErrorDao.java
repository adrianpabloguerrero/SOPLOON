package ar.edu.unicen.isistan.si.soploon.server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import ar.edu.unicen.isistan.si.soploon.server.models.Error;

public class ErrorDao {

	
	private static final String TABLE_NAME = "soploon.error";
	private static final String COLUMNS_INSERT = "(id_project,id_user,date,id_rule,version_rule,code_location,representation_location,reviewed)";
	private static final String VALUES = "(?,?,to_timestamp(?),?,?,to_json(?::json),to_json(?::json),?)";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS_INSERT + " VALUES";
	private static final String SINGLE_INSERT= INSERT+ " " + VALUES + ";";
	private static final String CONDITION_ID = " WHERE id = ? ";
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " " + CONDITION_ID + ";";
		
	private Database database;
	
	public ErrorDao (Database database) {
		this.database = database;
	}
	

	
	
	public boolean insert(List<Error> errors) throws SQLException {
		
		Gson gson = new Gson();
		
		String query = INSERT;
		Object[] args = new Object[8*errors.size()];

		int index = 0;
		for (Error error: errors) {
		args[index+0] = error.getProjectId();
		args[index+1] = error.getUserId();
		args[index+2] = error.getDate();
		args[index+3] = error.getRuleId();
		args[index+4] = error.getVersionRule();
		args[index+5] = gson.toJson(error.getCodeLocation());
		args[index+6] = gson.toJson(error.getRepresentationLocation());
		args[index+7] = error.getReviewed();
		if (index != 0)
			query += ", " + VALUES;
		else 
			query += " " + VALUES;
		index += 8;
		}
		
		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection,SINGLE_INSERT,args)) {
			int modifiedRows = statement.executeUpdate();
			if (modifiedRows > 1) {
				ResultSet keys = statement.getGeneratedKeys();
				for (Error error: errors) {
				keys.next();
				int id = keys.getInt(1);
				error.setId(id);
				}
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
}

package isistan.soploon.services.resources.error;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import isistan.soploon.database.Database;

public class ErrorDao {

	
	private static final String TABLE_NAME = "soploon.error";
	private static final String COLUMNS_INSERT = "(user_id,project_id,date,id_rule,version_rule,code_location,representation_location,reviewed)";
	private static final String VALUES = "(?,?,to_timestamp(?),?,?,to_json(?::json),to_json(?::json),?)";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS_INSERT + " VALUES";
	private static final String SINGLE_INSERT= INSERT+ " " + VALUES + ";";
	private static final String CONDITION_ID = " WHERE id = ? ";
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " " + CONDITION_ID + ";";
	
	
	private Database database;
	
	public ErrorDao (Database database) {
		this.database = database;
	}
	
	public boolean insert(Error error) throws SQLException {
		
		Gson gson = new Gson();
		
		Object[] args = new Object[7];
		args[0] = error.getUserId();
		args[1] = error.getProjectId();
		args[2] = error.getDate();
		args[3] = error.getIdRule();
		args[4] = error.getVersionRule();
		args[5] = gson.toJson(error.getCodeLocation());
		args[6] = gson.toJson(error.getRepresentationLocation());
		args[7] = error.getReviewed();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection,SINGLE_INSERT,args)) {
			int modifiedRows = statement.executeUpdate();
			if (modifiedRows == 1) {
				ResultSet keys = statement.getGeneratedKeys();
				keys.next();
				int id = keys.getInt(1);
				error.setId(id);
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

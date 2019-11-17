package isistan.soploon.services.resources.correction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import isistan.soploon.database.Database;

public class CorrectionDao {


	private static final String TABLE_NAME = "soploon.correction";
	private static final String COLUMNS_INSERT = "(user_id,project_id,date,code,representation,version)";
	private static final String VALUES = "(?,?,to_timestamp(?),to_json(?::json),to_json(?::json),?)";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS_INSERT + " VALUES";
	private static final String SINGLE_INSERT= INSERT+ " " + VALUES + ";";
	private static final String CONDITION_ID = " WHERE id = ? ";
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " " + CONDITION_ID + ";";
	
	private Database database;

	public CorrectionDao(Database database) {
		this.database = database;
	}
	
	public boolean insert(Correction correction) throws SQLException {
		Gson gson = new Gson();

		Object[] args = new Object[6];
		args[0] = correction.getUserId();
		args[1] = correction.getProjectId();
		args[2] = correction.getDate();
		args[3] = gson.toJson(correction.getCode());
		args[4] = gson.toJson(correction.getRepresentation());
		args[5] = correction.getVersion();
		
		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection,SINGLE_INSERT,args)) {
			int modifiedRows = statement.executeUpdate();
			if (modifiedRows == 1) {
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



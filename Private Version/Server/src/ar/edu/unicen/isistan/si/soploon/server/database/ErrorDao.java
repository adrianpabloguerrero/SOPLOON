package ar.edu.unicen.isistan.si.soploon.server.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ar.edu.unicen.isistan.si.soploon.server.models.CodeLocation;
import ar.edu.unicen.isistan.si.soploon.server.models.Error;

public class ErrorDao {

	
	private static final String TABLE_NAME = "soploon.error";
	private static final String COLUMNS_INSERT = "(id_project,id_user,date,id_rule,version_rule,code_location,representation_location,reviewed)";
	private static final String VALUES = "(?,?,to_timestamp(?),?,?,to_json(?::json),to_json(?::json),?)";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS_INSERT + " VALUES";
	private static final String CONDITION_CORRECTION = " WHERE id_user = ? AND id_project = ? AND date = ? ";
	private static final String SELECT_BY_CORRECTION = "SELECT * FROM " + TABLE_NAME + " " + CONDITION_CORRECTION + ";";
	private static final String CONDITION_ID = " WHERE id_user = ? AND id_project = ? AND date = ? AND id = ?";
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " " + CONDITION_ID + ";";

	private Database database;
	
	public ErrorDao (Database database) {
		this.database = database;
	}
	
	public ArrayList<Error> getErrorsByCorrection(int userId, int projectId, long time) throws SQLException {
		ArrayList<Error> out = new ArrayList<>();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_CORRECTION, userId ,projectId, new Date(time))) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Error error = this.readRow(result);
				out.add(error);
			}
			return out;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public Error getErrorsById(int userId, int projectId, long time, int id) throws SQLException {
		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_ID, userId ,projectId, new Date(time),id)) {
			ResultSet result = statement.executeQuery();
			if (result.next())
				return this.readRow(result);
			else
				return null;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
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
		try (PreparedStatement statement = this.database.getStatement(connection,query,args)) {
			int modifiedRows = statement.executeUpdate();
			if (modifiedRows >= 1) {
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

	
	private Error readRow(ResultSet result) throws SQLException {
		Gson gson = new Gson();
		Error error = new Error();
		error.setId(result.getInt(1));
		error.setProjectId(result.getInt(2));
		error.setUserId(result.getInt(3));
		error.setDate(result.getDate(4).getTime());
		error.setRuleId(result.getInt(5));
		error.setVersionRule(result.getInt(6));
		error.setCodeLocation(gson.fromJson(result.getString(7), new TypeToken<ArrayList<CodeLocation>>() {}.getType()));
		error.setRepresentationLocation(gson.fromJson(result.getString(8),  new TypeToken<ArrayList<Integer>>() {}.getType()));
		error.setReviewed(result.getInt(9));
		return error;
	}
	
}

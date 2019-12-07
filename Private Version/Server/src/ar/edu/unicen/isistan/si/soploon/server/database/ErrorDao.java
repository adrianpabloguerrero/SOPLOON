package ar.edu.unicen.isistan.si.soploon.server.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
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
	private static final String CONDITION_ID = " id = ? ";
	private static final String CONDITION_DATE = " date BETWEEN ? AND ? ";
	private static final String CONDITION_USER = " id_user = ? ";
	private static final String CONDITION_RULE = " id_rule = ? ";
	private static final String CONDITION_CORRECTION = " WHERE id_user = ? AND id_project = ? AND date = ? ";
	private static final String SIMPLE_SELECT = "SELECT * FROM " + TABLE_NAME + " " + "WHERE" + " ";
	private static final String SELECT_BY_CORRECTION = SIMPLE_SELECT + CONDITION_CORRECTION + ";";
	private static final String SELECT_BY_ID = SIMPLE_SELECT + CONDITION_ID + ";";
	private static final String SELECT_BY_RULE_DATE = SIMPLE_SELECT + CONDITION_RULE + " AND " + CONDITION_DATE + ";";
	private static final String SELECT_BY_USER_DATE = SIMPLE_SELECT + CONDITION_USER + " AND " + CONDITION_DATE + ";";
	private static final String SELECT_BY_USER_RULE_DATE = SIMPLE_SELECT + CONDITION_USER + " AND " + CONDITION_RULE + " AND " + CONDITION_DATE + ";";
	private static final String SELECT_BETWEEN_DATE = SIMPLE_SELECT + CONDITION_DATE + ";";
	private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET "+ "id = ? , id_project = ? , id_user = ? , date = to_timestamp(?) , id_rule = ? , version_rule = ?, code_location = to_json(?::json) , representation_location = to_json(?::json), reviewed = ? " + CONDITION_ID;

	private Database database;
	
	public ErrorDao (Database database) {
		this.database = database;
	}

	public ArrayList<Error> getErrorsByCorrection(int userId, int projectId, long time) throws SQLException {
		ArrayList<Error> out = new ArrayList<>();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_CORRECTION, userId ,projectId, new Date(time*1000))) {
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

	public Error getErrorsById(int id) throws SQLException {
		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_ID, id)) {
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
	
	public ArrayList<Error> getErrorsByUserBetweenDate(int userId,long dateStart, long dateEnd) throws SQLException{
		ArrayList<Error> out = new ArrayList<>();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_USER_DATE, userId,new Timestamp (dateStart*1000) , new Timestamp (dateEnd*1000))) {
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

	public ArrayList<Error> getErrorsByRuleBetweenDate(int ruleId,long dateStart, long dateEnd) throws SQLException{
		ArrayList<Error> out = new ArrayList<>();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_RULE_DATE, ruleId,new Timestamp (dateStart*1000) , new Timestamp (dateEnd*1000))) {
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
	
	public ArrayList<Error> getErrorsByUserAndRuleBetweenDate(int userId, int ruleId,long dateStart, long dateEnd) throws SQLException{
		ArrayList<Error> out = new ArrayList<>();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_USER_RULE_DATE, userId,ruleId,new Timestamp (dateStart*1000) , new Timestamp (dateEnd*1000))) {
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
	
	public ArrayList<Error> getErrorsBetweenDate(long dateStart, long dateEnd) throws SQLException{
		ArrayList<Error> out = new ArrayList<>();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BETWEEN_DATE, new Timestamp (dateStart*1000) , new Timestamp (dateEnd*1000))) {
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

	public boolean updateError(Error error) throws SQLException {

		Gson gson = new Gson();

		Object[] args = new Object[10];
		args[0] = error.getId();
		args[1] = error.getProjectId();
		args[2] = error.getUserId();
		args[3] = error.getDate();
		args[4] = error.getRuleId();
		args[5] = error.getVersionRule();
		args[6] = gson.toJson(error.getCodeLocation());
		args[7] = gson.toJson(error.getRepresentationLocation());
		args[8] = error.getReviewed();
		args[9] = error.getId();

		Connection connection = this.database.connection();

		try (PreparedStatement statement = this.database.getStatement(connection, UPDATE, args)) {
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

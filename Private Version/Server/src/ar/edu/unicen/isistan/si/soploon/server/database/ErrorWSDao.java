package ar.edu.unicen.isistan.si.soploon.server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ar.edu.unicen.isistan.si.soploon.server.models.CodeLocation;
import ar.edu.unicen.isistan.si.soploon.server.models.ErrorWS;

public class ErrorWSDao {

	private static final String TABLE_NAME = "soploon.complete_error";
	private static final String CONDITION_ID = " id = ? ";
	private static final String CONDITION_DATE = " date BETWEEN ? AND ? ";
	private static final String CONDITION_USER = " id_user = ? ";
	private static final String CONDITION_RULE = " id_rule = ? ";
	private static final String CONDITION_CORRECTION = " id_user = ? AND id_project = ? AND date = ? ";
	private static final String SIMPLE_SELECT = "SELECT * FROM " + TABLE_NAME + " " + "WHERE" + " ";
	private static final String SELECT_BY_CORRECTION = SIMPLE_SELECT + CONDITION_CORRECTION + ";";
	private static final String SELECT_BY_ID = SIMPLE_SELECT + CONDITION_ID + ";";
	private static final String SELECT_BY_RULE_DATE = SIMPLE_SELECT + CONDITION_RULE + " AND " + CONDITION_DATE + ";";
	private static final String SELECT_BY_USER_DATE = SIMPLE_SELECT + CONDITION_USER + " AND " + CONDITION_DATE + ";";
	private static final String SELECT_BY_USER_RULE_DATE = SIMPLE_SELECT + CONDITION_USER + " AND " + CONDITION_RULE + " AND " + CONDITION_DATE + ";";
	private static final String SELECT_BETWEEN_DATE = SIMPLE_SELECT + CONDITION_DATE + ";";

	private Database database;
	
	public ErrorWSDao (Database database) {
		this.database = database;
	}

	public ArrayList<ErrorWS> getErrorsByCorrection(int userId, int projectId, long time) throws SQLException {
		ArrayList<ErrorWS> out = new ArrayList<>();

		try (Connection connection = this.database.connection();PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_CORRECTION, userId ,projectId, time)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ErrorWS errorWS = this.readRow(result);
				out.add(errorWS);
			}
			return out;
		} catch (SQLException e) {
			throw e;
		}
	}

	public ErrorWS getErrorsById(int id) throws SQLException {
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
	
	public ArrayList<ErrorWS> getErrorsByUserBetweenDate(int userId,long dateStart, long dateEnd) throws SQLException{
		ArrayList<ErrorWS> out = new ArrayList<>();

		try (Connection connection = this.database.connection();PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_USER_DATE, userId, dateStart, dateEnd)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ErrorWS errorWS = this.readRow(result);
				out.add(errorWS);
			}
			return out;
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList<ErrorWS> getErrorsByRuleBetweenDate(int ruleId,long dateStart, long dateEnd) throws SQLException{
		ArrayList<ErrorWS> out = new ArrayList<>();

		try (Connection connection = this.database.connection();PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_RULE_DATE, ruleId, dateStart, dateEnd)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ErrorWS errorWS = this.readRow(result);
				out.add(errorWS);
			}
			return out;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public ArrayList<ErrorWS> getErrorsByUserAndRuleBetweenDate(int userId, int ruleId,long dateStart, long dateEnd) throws SQLException{
		ArrayList<ErrorWS> out = new ArrayList<>();

		try (Connection connection = this.database.connection();PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_USER_RULE_DATE, userId,ruleId, dateStart, dateEnd)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ErrorWS errorWS = this.readRow(result);
				out.add(errorWS);
			}
			return out;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public ArrayList<ErrorWS> getErrorsBetweenDate(long dateStart, long dateEnd) throws SQLException{
		ArrayList<ErrorWS> out = new ArrayList<>();

		try (Connection connection = this.database.connection();PreparedStatement statement = this.database.getStatement(connection, SELECT_BETWEEN_DATE, dateStart, dateEnd)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ErrorWS errorWS = this.readRow(result);
				out.add(errorWS);
			}
			return out;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	
	private ErrorWS readRow(ResultSet result) throws SQLException {
		Gson gson = new Gson();
		ErrorWS errorWS = new ErrorWS();
		errorWS.setId(result.getInt(1));
		errorWS.setProjectId(result.getInt(2));
		errorWS.setUserId(result.getInt(3));
		errorWS.setDate(result.getLong(4));
		errorWS.setRuleId(result.getInt(5));
		errorWS.setVersionRule(result.getInt(6));
		errorWS.setCodeLocation(gson.fromJson(result.getString(7), new TypeToken<ArrayList<CodeLocation>>() {}.getType()));
		errorWS.setRepresentationLocation(gson.fromJson(result.getString(8),  new TypeToken<ArrayList<Integer>>() {}.getType()));
		errorWS.setReviewed(result.getInt(9));
		errorWS.setNameProject(result.getString(10));
		errorWS.setNameUser(result.getString(11));
		errorWS.setNameRule(result.getString(12));
		return errorWS;
	}


	

	

}

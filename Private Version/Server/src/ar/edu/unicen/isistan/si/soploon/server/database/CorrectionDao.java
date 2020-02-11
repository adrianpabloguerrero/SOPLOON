package ar.edu.unicen.isistan.si.soploon.server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ar.edu.unicen.isistan.si.soploon.server.models.Correction;
import ar.edu.unicen.isistan.si.soploon.server.models.SourceCode;
import ar.edu.unicen.isistan.si.soploon.server.models.Configuration;

public class CorrectionDao {
	
	private static final String ID_USER_COL = "id_user";
	private static final String ID_PROJECT_COL = "id_project";
	private static final String DATE_COL = "date";
	private static final String CODE_COL = "code";
	private static final String REPRESENTATION_COL = "representation";
	private static final String VERSION_SOPLOON_COL = "version_soploon";

	private static final String TABLE_NAME = "soploon.correction";
	private static final String COLUMNS_INSERT = "(id_user,id_project,date,code,representation,version_soploon)";
	private static final String VALUES = "(?,?,?,to_json(?::json),to_json(?::json),to_json(?::json))";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS_INSERT + " VALUES";
	private static final String SINGLE_INSERT= INSERT+ " " + VALUES + ";";
	private static final String CONDITION_ID = " WHERE id_user = ? AND id_project = ? AND date = ?";
	private static final String CONDITION_PROJECT = " WHERE id_user = ? AND id_project = ?";
	private static final String SIMPLE_SELECT = "SELECT * FROM " + TABLE_NAME + " " ;
	private static final String REDUCED_SELECT = "SELECT id_user, id_project, date, version_soploon FROM " + TABLE_NAME + " " ;
	private static final String SELECT_BY_PROJECT = REDUCED_SELECT + CONDITION_PROJECT + ";";
	private static final String SELECT_BY_ID = SIMPLE_SELECT + CONDITION_ID + ";";
	
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
		args[5] = gson.toJson(correction.getConfiguration());
		
		try (Connection connection = this.database.connection(); PreparedStatement statement = this.database.getStatement(connection,SINGLE_INSERT,args)) {
			int modifiedRows = statement.executeUpdate();
			return (modifiedRows == 1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Correction> getCorrectionsByProject(int userId, int projectId) throws SQLException {
		ArrayList<Correction> out = new ArrayList<>();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_PROJECT, userId ,projectId)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Correction correction = this.readReducedRow(result);
				out.add(correction);
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

	public Correction getCorrection(int userId, int projectId, long time) throws SQLException {
		Connection connection = this.database.connection();
		
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_ID, userId ,projectId, time)) {
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
	
	private Correction readRow(ResultSet result) throws SQLException {
		Gson gson = new Gson();
		Correction correction = new Correction();
		correction.setProjectId(result.getInt(ID_USER_COL));
		correction.setUserId(result.getInt(ID_USER_COL));
		correction.setDate(result.getLong(DATE_COL));
		correction.setCode(gson.fromJson(result.getString(CODE_COL), new TypeToken<ArrayList<SourceCode>>() {}.getType()));
		correction.setRepresentation(gson.fromJson(result.getString(REPRESENTATION_COL), new TypeToken<ArrayList<String>>() {}.getType()));
		correction.setConfiguration(gson.fromJson(result.getString(VERSION_SOPLOON_COL), Configuration.class));
		return correction;
	}
	
	private Correction readReducedRow(ResultSet result) throws SQLException {
		Gson gson = new Gson();
		Correction correction = new Correction();
		correction.setProjectId(result.getInt(ID_PROJECT_COL));
		correction.setUserId(result.getInt(ID_USER_COL));
		correction.setDate(result.getLong(DATE_COL));
		correction.setConfiguration(gson.fromJson(result.getString(VERSION_SOPLOON_COL), Configuration.class));
		return correction;
	}
} 



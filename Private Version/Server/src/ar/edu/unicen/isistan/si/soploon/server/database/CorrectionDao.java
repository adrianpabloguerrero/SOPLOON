package ar.edu.unicen.isistan.si.soploon.server.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ar.edu.unicen.isistan.si.soploon.server.models.Correction;
import ar.edu.unicen.isistan.si.soploon.server.models.SourceCode;

public class CorrectionDao {

	private static final String TABLE_NAME = "soploon.correction";
	private static final String COLUMNS_INSERT = "(id_user,id_project,date,code,representation,version_soploon)";
	private static final String VALUES = "(?,?,to_timestamp(?),to_json(?::json),to_json(?::json),?)";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS_INSERT + " VALUES";
	private static final String SINGLE_INSERT= INSERT+ " " + VALUES + ";";
	private static final String CONDITION_ID = " WHERE id_user = ? AND id_project = ? AND date = ?";
	private static final String CONDITION_PROJECT = " WHERE id_user = ? AND id_project = ?";
	private static final String SIMPLE_SELECT = "SELECT * FROM " + TABLE_NAME + " " ;
	private static final String SELECT_BY_PROJECT = SIMPLE_SELECT + CONDITION_PROJECT + ";";
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
		args[5] = correction.getVersionSoploon();
		
		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection,SINGLE_INSERT,args)) {
			int modifiedRows = statement.executeUpdate();
			if (modifiedRows == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public ArrayList<Correction> getCorrectionsByProject(int userId, int projectId) throws SQLException {
		ArrayList<Correction> out = new ArrayList<>();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_PROJECT, userId ,projectId)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Correction correction = this.readRow(result);
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
		
		//TODO 	revisar *1000 en date. 
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_ID, userId ,projectId,new Timestamp(time*1000))) {
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
		correction.setProjectId(result.getInt(1));
		correction.setUserId(result.getInt(2));
		correction.setDate(result.getDate(3).getTime());
		correction.setCode(gson.fromJson(result.getString(4), new TypeToken<ArrayList<SourceCode>>() {}.getType()));
		correction.setRepresentation(gson.fromJson(result.getString(5), new TypeToken<ArrayList<String>>() {}.getType()));
		correction.setVersionSoploon(result.getString(6));
		return correction;
	}
	
} 



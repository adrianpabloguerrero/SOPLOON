package isistan.soploon.models.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import isistan.soploon.database.Database;

public class ProjectDao {
	private static final String TABLE_NAME = "soploon.project";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + " VALUES";
	private static final String VALUES = "(?,to_timestamp(?),to_json(?::json),?,?)";
	private static final String SINGLE_INSERT = INSERT + " " + VALUES + ";";
	private static final String CONDITION_ID = "WHERE iduser = ? ";
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " " + CONDITION_ID;
	private static final String MODIFY = "UPDATE " + TABLE_NAME + " SET "
			+ "iduser = ? , date = to_timestamp(?) , code = to_json(?::json) , representation = ? , soploonVersion = ? " + CONDITION_ID;

	private Database database;

	public ProjectDao(Database database) {
		this.database = database;
	}

	public boolean insert(Project project) {

		Gson gson = new Gson();

		Object[] args = new Object[5];
		args[0] = project.getIdUser();
		args[1] = project.getDate();
		args[2] = gson.toJson(project.getCode());
		args[3] = project.getRepresentation();
		args[4] = project.getSoploonVersion();

		return this.database.insert(SINGLE_INSERT, args) == 1;
	}

	public Project getProjectByIdUser(int idUser) {
		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_ID, idUser)) {
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				Project project = new Project();
				project.setIdUser(result.getInt(1));
				project.setDate(result.getDate(2).getTime());
				// project.setCode(?);
				project.setRepresentation(result.getString(4));
				project.setSoploonVersion(result.getString(5));
				return project;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;
	}

	public boolean updateProject(int id, Project project) {
		
		Gson gson = new Gson();

		Object[] args = new Object[6];
		args[0] = project.getIdUser();
		args[1] = project.getDate();
		args[2] = gson.toJson(project.getCode());
		args[3] = project.getRepresentation();
		args[4] = project.getSoploonVersion();
		args[5] = id;
		
		return this.database.insert(MODIFY, args)==1;
	}

}

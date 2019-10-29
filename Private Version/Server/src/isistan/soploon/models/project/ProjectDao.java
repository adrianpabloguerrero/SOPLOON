package isistan.soploon.models.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import isistan.soploon.database.Database;

public class ProjectDao {
	private static final String TABLE_NAME = "soploon.project";
	private static final String COLUMNS = "(user_id,name)";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS + " VALUES";
	private static final String VALUES = "(?,?)";
	private static final String SINGLE_INSERT = INSERT + " " + VALUES + ";";
	private static final String CONDITION_USER_ID = "WHERE user_id = ? ";
	private static final String CONDITION_ID = "WHERE id = ? ";
	private static final String SELECT_BY_ID_USER = "SELECT * FROM " + TABLE_NAME + " " + CONDITION_USER_ID + ";";
	private static final String MODIFY = "UPDATE " + TABLE_NAME + " SET "
			+ "iduser = ? , date = to_timestamp(?) , code = to_json(?::json) , representation = ? , soploonVersion = ? " + CONDITION_ID;
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " " + CONDITION_ID + ";";

	private Database database;

	public ProjectDao(Database database) {
		this.database = database;
	}

	public boolean insert(Project project) throws SQLException {

		Object[] args = new Object[2];
		args[0] = project.getUserId();
		args[1] = project.getName();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection,SINGLE_INSERT,args)) {
			int modifiedRows = statement.executeUpdate();
			if (modifiedRows == 1) {
				ResultSet keys = statement.getGeneratedKeys();
				keys.next();
				int id = keys.getInt(2);
				project.setId(id);
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

	public ArrayList <Project> getProjectByIdUser(int idUser) throws SQLException {
		
		ArrayList<Project> out = new ArrayList<>();

 		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection,SELECT_BY_ID_USER,idUser)) {
			ResultSet result = statement.executeQuery(); 

			while (result.next()) {
				Project project = new Project();
				project.setIdUser(result.getInt(1));
				project.setId(result.getInt(2));
				project.setName(result.getString(3));
				out.add(project);
			}
			return out;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
/*
	public boolean updateProject(int id, Project project) {
		
		Gson gson = new Gson();

		Object[] args = new Object[6];
		args[0] = project.getUserId();
		args[1] = project.getDate();
		args[2] = gson.toJson(project.getCode());
		args[3] = project.getRepresentation();
		args[4] = project.getSoploonVersion();
		args[5] = id;
		//TODO hacer esto con getStatement
		return false;
		//return this.database.insert(MODIFY, args)==1;
	}
*/

	public Project getProjectById(int id) throws SQLException {
		Connection connection = this.database.connection();

		try (PreparedStatement statement = this.database.getStatement(connection,SELECT_BY_ID,id)) {
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				Project project = new Project();
				project.setIdUser(result.getInt(1));
				project.setId(result.getInt(2));
				project.setName(result.getString(3));
				return project;
			} else {
				return null;
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

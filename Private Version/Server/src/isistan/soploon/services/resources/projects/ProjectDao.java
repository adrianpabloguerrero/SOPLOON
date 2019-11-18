package isistan.soploon.services.resources.projects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import isistan.soploon.database.Database;

public class ProjectDao {

	private static final String TABLE_NAME = "soploon.project";
	private static final String COLUMNS = "(id_user,name)";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS + " VALUES";
	private static final String VALUES = "(?,?)";
	private static final String SINGLE_INSERT = INSERT + " " + VALUES + ";";
	private static final String CONDITION_USER_ID = "WHERE id_user = ? ";
	private static final String CONDITION_ID = " WHERE id_user = ? AND id = ?";
	private static final String SELECT_BY_ID_USER = "SELECT * FROM " + TABLE_NAME + " " + CONDITION_USER_ID + ";";
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " " + CONDITION_ID + ";";
	private static final String SELECT_ALL_PROJECTS = "SELECT * FROM " + TABLE_NAME + ";";

	private Database database;

	public ProjectDao(Database database) {
		this.database = database;
	}

	public boolean insert(Project project) throws SQLException {

		Object[] args = new Object[2];
		args[0] = project.getUserId();
		args[1] = project.getName();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SINGLE_INSERT, args)) {
			int modifiedRows = statement.executeUpdate();
			if (modifiedRows == 1) {
				ResultSet keys = statement.getGeneratedKeys();
				keys.next();
				int id = keys.getInt(1);
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

	public ArrayList<Project> getProjectsByUser(int idUser) throws SQLException {
		ArrayList<Project> out = new ArrayList<>();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_ID_USER, idUser)) {
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Project project = readRow(result);
				out.add(project);
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

	public Project getProject(int user_id, int project_id) throws SQLException {
		Connection connection = this.database.connection();

		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_ID, user_id, project_id)) {
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				Project project = readRow(result);
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

	public ArrayList<Project> getProjects() throws SQLException {
		ArrayList<Project> out = new ArrayList<>();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_ALL_PROJECTS)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Project project = readRow(result);
				out.add(project);
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
	
	private Project readRow(ResultSet result) throws SQLException {
		Project project = new Project();
		project.setId(result.getInt(1));
		project.setIdUser(result.getInt(2));
		project.setName(result.getString(3));
		return project;
	}
	
}

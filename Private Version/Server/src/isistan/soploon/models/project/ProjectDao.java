package isistan.soploon.models.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import isistan.soploon.database.Database;
import isistan.soploon.models.user.User;

public class ProjectDao {
	private static final String TABLE_NAME = "soploon.project";
	private static final String COLUMNS = "(user_id,name)";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS + " VALUES";
	private static final String VALUES = "(?,?)";
	private static final String SINGLE_INSERT = INSERT + " " + VALUES + ";";
	private static final String CONDITION_USER_ID = "WHERE user_id = ? ";
	private static final String CONDITION_ID = " WHERE id = ? ";
	private static final String SELECT_BY_ID_USER = "SELECT * FROM " + TABLE_NAME + " " + CONDITION_USER_ID + ";";
	private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET "
			+ "user_id = ? , id = ? , name = ? " + CONDITION_ID;
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

	public boolean updateProject(int id, Project project) throws SQLException {

		Object[] args = new Object[4];
		args[0] = project.getUserId();
		args[1] = project.getId();
		args[2] = project.getName();
		args[3] = id;


		Connection connection = this.database.connection();

		try (PreparedStatement statement = this.database.getStatement(connection,UPDATE,args)) {
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

	public ArrayList<Project> getProjects() throws SQLException {
		ArrayList<Project> out = new ArrayList<>();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection,SELECT_ALL_PROJECTS)) {		
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Project project = new Project();
				project.setIdUser(result.getInt(1));
				project.setId(result.getInt(2));
				project.setName(result.getString(3));
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
}

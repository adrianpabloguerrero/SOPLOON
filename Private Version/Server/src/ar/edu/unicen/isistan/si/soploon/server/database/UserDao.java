package ar.edu.unicen.isistan.si.soploon.server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ar.edu.unicen.isistan.si.soploon.server.models.User;
import ar.edu.unicen.isistan.si.soploon.server.models.User.Role;

public class UserDao {
	
	private static final String TABLE_NAME = "soploon.user";
	private static final String COLUMNS = "(creation_date,name,role,password)";
	private static final String VALUES = "(?,?,?,?)";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS + " VALUES";
	private static final String SINGLE_INSERT = INSERT + " " + VALUES + ";";
	private static final String CONDITION_ID = " WHERE id = ? ";
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " " + CONDITION_ID + ";";
	private static final String SELECT_ALL_USERS = "SELECT * FROM " + TABLE_NAME + ";";
	private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET "+ "id = ? , creation_date = ? , name = ? , role = ?, password = ? " + CONDITION_ID;

	private Database database;

	public UserDao(Database database) {
		this.database = database;
	}

	public boolean insert(User user) throws SQLException {
		Object[] args = new Object[4];
		args[0] = user.getCreationDate();
		args[1] = user.getName();
		args[2] = user.getRole().toString();
		args[3] = user.getPassword();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SINGLE_INSERT, args)) {
			int modifiedRows = statement.executeUpdate();
			if (modifiedRows == 1) {
				ResultSet keys = statement.getGeneratedKeys();
				keys.next();
				int id = keys.getInt(1);
				user.setId(id);
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

	public User getUser(int id) throws SQLException {
		Connection connection = this.database.connection();

		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_ID, id)) {
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				User user = readRow(result);
				return user;
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

	public ArrayList<User> getUsers() throws SQLException {
		ArrayList<User> out = new ArrayList<>();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_ALL_USERS)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				User user = this.readRow(result);
				out.add(user);
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

	public boolean updateUser(User user) throws SQLException {


		Object[] args = new Object[6];
		args[0] = user.getId();
		args[1] = user.getCreationDate();
		args[2] = user.getName();
		args[3] = user.getRole().toString();
		args[4] = user.getPassword();
		args[5] = user.getId();

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
	
	private User readRow(ResultSet result) throws SQLException {
		User user = new User();
		user.setId(result.getInt(1));
		user.setCreationDate((result.getLong(2)));
		user.setName(result.getString(3));
		user.setRole(Role.valueOf(result.getString(4)));
		user.setPassword(result.getString(5));
		return user;
	}
	
}

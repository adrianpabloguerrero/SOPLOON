package isistan.soploon.models.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import isistan.soploon.database.Database;
import isistan.soploon.models.rule.Rule;

public class UserDao {
	private static final String TABLE_NAME = "soploon.user";
	private static final String COLUMNS = "(creation_date,name,role)";
	private static final String VALUES = "(to_timestamp(?),?,?)";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS + " VALUES";
	private static final String SINGLE_INSERT= INSERT+ " " + VALUES + ";";
	private static final String CONDITION_ID = " WHERE id = ? ";
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " " + CONDITION_ID + ";";

	private Database database;

	public UserDao(Database database) {
		this.database = database;
	}

	public boolean insert(User user) throws SQLException {
		Object[] args = new Object[3];
		args[0] = user.getCreationDate();
		args[1] = user.getName();
		args[2] = user.getRole();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection,SINGLE_INSERT,args)) {
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

		try (PreparedStatement statement = this.database.getStatement(connection,SELECT_BY_ID,id)) {
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				User user = new User();
				user.setId(result.getInt(1));
				user.setCreationDate((result.getDate(2).getTime()));
				user.setName(result.getString(3));
				user.setRole(result.getString(4));
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
}

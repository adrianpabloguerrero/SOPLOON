package ar.edu.unicen.isistan.si.soploon.server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.unicen.isistan.si.soploon.server.models.User;
import ar.edu.unicen.isistan.si.soploon.server.models.User.Role;

public class AuthenticationDao {

	private static final String TABLE_USER = "soploon.user";
	private static final String CONDITION_NAME = " WHERE name = ? ";
	private static final String SELECT_USER_PASSWORD_ROLE = "SELECT name, password, role FROM " + TABLE_USER + CONDITION_NAME;

	private Database database;

	public AuthenticationDao (Database database) {
		this.database = database;
	}
	
	public User getUser(String name) throws SQLException {
		try (Connection connection = this.database.connection(); PreparedStatement statement = this.database.getStatement(connection, SELECT_USER_PASSWORD_ROLE, name)) {
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				User user = new User();
				user.setName(result.getString(1));
				user.setPassword(result.getString(2));
				user.setRole(Role.valueOf(result.getString(3)));
				return user;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw e;
		}
	}
	
	
	
	
}

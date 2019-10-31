package isistan.soploon.models.predicate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import isistan.soploon.database.Database;

public class PredicateDao {
	private static final String TABLE_NAME = "soploon.predicate";
	private static final String COLUMNS_INSERT = "(name,description,code)";
	private static final String VALUES = "(?,?,?)";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS_INSERT + " VALUES";
	private static final String SINGLE_INSERT= INSERT+ " " + VALUES + ";";
	private static final String CONDITION_ID = " WHERE id = ? ";
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " " + CONDITION_ID + ";";
	private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET "
			+ "id = ? , version = ? , name = ? , description = ? , code = ? " + CONDITION_ID;
	private static final String SELECT_ALL_PREDICATE = "SELECT * FROM " + TABLE_NAME + ";";
	private static final String LAST_VERSION = "ORDER BY VERSION DESC LIMIT 1";
	private static final String SUB_QUERY_VERSION = " (SELECT version FROM " + TABLE_NAME  + CONDITION_ID + LAST_VERSION + ")";
	private static final String VALUES_NEW_VERSION = "(?, " + SUB_QUERY_VERSION + "+1," + "?,?,?)";
	private static final String INSERT_NEW_VERSION = " INSERT INTO " + TABLE_NAME + " VALUES " + VALUES_NEW_VERSION + ";";

	private Database database;


	public PredicateDao(Database database) {
		this.database = database;
	}

	public boolean insert(Predicate predicate) throws SQLException {
		Object[] args = new Object[3];
		args[0] = predicate.getName();
		args[1] = predicate.getDescription();
		args[2] = predicate.getCode();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection,SINGLE_INSERT,args)) {
			int modifiedRows = statement.executeUpdate();
			if (modifiedRows == 1) {
				ResultSet keys = statement.getGeneratedKeys();
				keys.next();
				int id = keys.getInt(1);
				predicate.setId(id);
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

	public ArrayList <Predicate> getPredicate(int id) throws SQLException {

		ArrayList<Predicate> out = new ArrayList<>();

		Connection connection = this.database.connection();

		try (PreparedStatement statement = this.database.getStatement(connection,SELECT_BY_ID,id)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Predicate predicate = new Predicate();
				predicate.setId(result.getInt(1));
				predicate.setVersion(result.getInt(2));
				predicate.setDescription(result.getString(3));
				predicate.setCode(result.getString(4));
				out.add(predicate);
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

	public ArrayList<Predicate> getPredicates() throws SQLException {

		ArrayList<Predicate> out = new ArrayList<>();

		Connection connection = this.database.connection();

		try (PreparedStatement statement = this.database.getStatement(connection,SELECT_ALL_PREDICATE)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Predicate predicate = new Predicate();
				predicate.setId(result.getInt(1));
				predicate.setVersion(result.getInt(2));
				predicate.setDescription(result.getString(3));
				predicate.setCode(result.getString(4));
				out.add(predicate);
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

	public boolean updatePredicate (int id, Predicate predicate) throws SQLException {

		Object[] args = new Object[5];
		args[0] = id;
		args[1] = id;
		args[2] = predicate.getName();
		args[3] = predicate.getDescription();
		args[4] = predicate.getCode();

		Connection connection = this.database.connection();

		try (PreparedStatement statement = this.database.getStatement(connection,INSERT_NEW_VERSION,args)) {
			int modifiedRows = statement.executeUpdate();
			if (modifiedRows == 1) {
				ResultSet keys = statement.getGeneratedKeys();
				keys.next();
				int version = keys.getInt(2);
				predicate.setId(id);
				predicate.setVersion(version);
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



}

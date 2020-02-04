package ar.edu.unicen.isistan.si.soploon.server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ar.edu.unicen.isistan.si.soploon.server.models.Predicate;

public class PredicateDao {
	private static final String TABLE_NAME = "soploon.predicate ";
	private static final String COLUMNS_INSERT = "(name,description,code,activated)";
	private static final String VALUES = "(?,?,?,?)";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS_INSERT + " VALUES";
	private static final String SINGLE_INSERT = INSERT + " " + VALUES + ";";
	private static final String LAST_VERSION = "ORDER BY VERSION DESC LIMIT 1";
	private static final String CONDITION_ID = " WHERE id = ? ";
	private static final String CONDITION_ID_VERSION = " WHERE id = ? AND version = ? ";
	private static final String SUB_QUERY_VERSION = " (SELECT version FROM " + TABLE_NAME + CONDITION_ID + LAST_VERSION
			+ ")";
	private static final String VALUES_NEW_VERSION = "(?, " + SUB_QUERY_VERSION + "+1," + "?,?,?,?)";
	private static final String INSERT_NEW_VERSION = " INSERT INTO " + TABLE_NAME + " VALUES " + VALUES_NEW_VERSION
			+ ";";
	private static final String SET_ACTIVATED_BY_ID = " UPDATE " + TABLE_NAME + " SET " + "activated = " + "?" + CONDITION_ID_VERSION + ";";;
	private static final String SET_FALSE_BY_ID = " UPDATE " + TABLE_NAME + " SET " + "activated = false" + CONDITION_ID
			+ ";";
	private static final String SIMPLE_SELECT = "SELECT * FROM " + TABLE_NAME;
	private static final String SELECT_BY_ID = SIMPLE_SELECT + CONDITION_ID + LAST_VERSION + ";";
	private static final String SELECT_ALL_PREDICATES = SIMPLE_SELECT + " ORDER BY id, version;";
	private static final String SELECT_BY_ID_VERSIONS = SIMPLE_SELECT + CONDITION_ID;
	
	private Database database;

	public PredicateDao(Database database) {
		this.database = database;
	}

	public boolean insert(Predicate predicate) throws SQLException {
		Object[] args = new Object[4];
		args[0] = predicate.getName();
		args[1] = predicate.getDescription();
		args[2] = predicate.getCode();
		args[3] = predicate.getActivated();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SINGLE_INSERT, args)) {
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

	public Predicate getPredicate(int id) throws SQLException {

		Connection connection = this.database.connection();

		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_ID, id)) {
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				Predicate predicate = this.readRow(result);
				return predicate;
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

	public ArrayList<Predicate> getPredicates() throws SQLException {
		ArrayList<Predicate> out = new ArrayList<>();

		Connection connection = this.database.connection();

		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_ALL_PREDICATES)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Predicate predicate = this.readRow(result);
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

	public boolean newVersion(int id, Predicate predicate) throws SQLException {

		// SET FALSE
		Object[] argsSF = new Object[1];
		argsSF[0] = id;

		// NEW VERSION
		Object[] argsNV = new Object[6];
		argsNV[0] = id;
		argsNV[1] = id;
		argsNV[2] = predicate.getName();
		argsNV[3] = predicate.getDescription();
		argsNV[4] = predicate.getCode();
		argsNV[5] = predicate.getActivated();

		Connection connection = this.database.connection();

		try {
			connection.setAutoCommit(false);

			PreparedStatement statementSetFalse = this.database.getStatement(connection, SET_FALSE_BY_ID, argsSF);
			PreparedStatement statementInsertVersion = this.database.getStatement(connection, INSERT_NEW_VERSION,
					argsNV);

			statementSetFalse.executeUpdate();
			statementInsertVersion.executeUpdate();

			ResultSet rs = statementInsertVersion.getGeneratedKeys();
			connection.commit();

			if (rs.next()) {
				predicate.setId(id);
				predicate.setVersion(rs.getInt("version"));
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			connection.setAutoCommit(true);
			if (connection != null) {
				connection.close();
			}
		}

	}

	private Predicate readRow(ResultSet result) throws SQLException {
		Predicate predicate = new Predicate();
		predicate.setId(result.getInt(1));
		predicate.setVersion(result.getInt(2));
		predicate.setName(result.getString(3));
		predicate.setDescription(result.getString(4));
		predicate.setCode(result.getString(5));
		predicate.setActivated(result.getBoolean(6));
		return predicate;
	}

	public ArrayList<Predicate> getPredicateVersions(int id) throws SQLException {
		ArrayList<Predicate> out = new ArrayList<>();

		Connection connection = this.database.connection();

		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_BY_ID_VERSIONS, id)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Predicate predicate = this.readRow(result);
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

	public boolean editPredicate(int id, Predicate predicate) throws SQLException {

		// SET FALSE
		Object[] argsSF = new Object[1];
		argsSF[0] = id;

		// SET ACTIVATED
		Object[] argsSA = new Object[3];
		argsSA[0] = predicate.getActivated();
		argsSA[1] = predicate.getId();
		argsSA[2] = predicate.getVersion();

		Connection connection = this.database.connection();

		try {
			connection.setAutoCommit(false);

			PreparedStatement statementSetFalse = this.database.getStatement(connection, SET_FALSE_BY_ID, argsSF);
			PreparedStatement statementInsertVersion = this.database.getStatement(connection, SET_ACTIVATED_BY_ID,
					argsSA);

			statementSetFalse.executeUpdate();
			statementInsertVersion.executeUpdate();

			ResultSet rs = statementInsertVersion.getGeneratedKeys();
			connection.commit();

			if (rs.next()) {
				predicate.setId(id);
				predicate.setVersion(rs.getInt("version"));
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			connection.setAutoCommit(true);
			if (connection != null) {
				connection.close();
			}
		}
	}
}

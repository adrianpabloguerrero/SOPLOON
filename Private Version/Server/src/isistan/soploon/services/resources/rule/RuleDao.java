package isistan.soploon.services.resources.rule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import isistan.soploon.database.Database;

public class RuleDao {

	private static final String TABLE_NAME = "soploon.rule";
	private static final String COLUMNS_INSERT = "(version,name,description,link,query,predicate,activated)";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS_INSERT + " VALUES";
	private static final String VALUES = "(?,?,?,?,?,?,?)";
	private static final String SINGLE_INSERT= INSERT+ " " + VALUES + ";";
	private static final String LAST_VERSION = "ORDER BY VERSION DESC LIMIT 1";
	private static final String CONDITION_ID = " WHERE id = ? ";
	private static final String SIMPLE_SELECT = "SELECT * FROM " + TABLE_NAME ;
	private static final String SELECT_BY_ID = SIMPLE_SELECT + " " + CONDITION_ID + LAST_VERSION;
	private static final String SET_FALSE_BY_ID = " UPDATE " + TABLE_NAME + " SET " + "activated = false" + CONDITION_ID + ";";
	private static final String SUB_QUERY_VERSION = " (SELECT version FROM " + TABLE_NAME  + CONDITION_ID + LAST_VERSION + ")";
	private static final String VALUES_NEW_VERSION = "(?, " + SUB_QUERY_VERSION + "+1," + "?,?,?,?,?,?)";
	private static final String INSERT_NEW_VERSION = " INSERT INTO " + TABLE_NAME + " VALUES " + VALUES_NEW_VERSION + ";";
	private static final String SELECT_ACTIVATED_RULES = SIMPLE_SELECT + " WHERE activated = true;" ;
	private static final String SELECT_BY_ID_VERSIONS = SIMPLE_SELECT + CONDITION_ID;

	private Database database;

	public RuleDao(Database database) {
		this.database = database;
	}

	public boolean insert(Rule rule) throws SQLException {
		Object[] args = new Object[7];
		args[0] = rule.getVersion();
		args[1] = rule.getName();
		args[2] = rule.getDescription();
		args[3]	= rule.getLink();
		args[4] = rule.getQuery();
		args[5] = rule.getPredicate();
		args[6] = rule.getActivated();

		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection,SINGLE_INSERT,args)) {
			int modifiedRows = statement.executeUpdate();
			if (modifiedRows == 1) {
				ResultSet keys = statement.getGeneratedKeys();
				keys.next();
				int id = keys.getInt(1);
				rule.setId(id);
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

	public Rule getRule(int id) throws SQLException {
		Connection connection = this.database.connection();

		try (PreparedStatement statement = this.database.getStatement(connection,SELECT_BY_ID,id)) {
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				Rule rule = this.readRow(result);
				return rule;
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

	public ArrayList<Rule> getRules() throws SQLException {
		ArrayList<Rule> out = new ArrayList<>();

		Connection connection = this.database.connection();

		try (PreparedStatement statement = this.database.getStatement(connection,SELECT_ACTIVATED_RULES)) {
			ResultSet result = statement.executeQuery(); 
			while (result.next()) {
				Rule rule = this.readRow(result);
				out.add(rule);
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

	public boolean updateRule(int id, Rule rule) throws SQLException {

		Object [] argsSF = new Object [1];
		argsSF [0] = id;

		Object[] argsNV = new Object[8];
		argsNV[0] = id;
		argsNV[1] = id;
		argsNV[2] = rule.getName();
		argsNV[3] = rule.getDescription();
		argsNV[4] = rule.getLink();
		argsNV[5] = rule.getQuery();
		argsNV[6] = rule.getPredicate();
		argsNV[7] = rule.getActivated();	

		Connection connection = this.database.connection();

		try {
			connection.setAutoCommit(false);

			PreparedStatement statementSetFalse = this.database.getStatement(connection,SET_FALSE_BY_ID,argsSF); 
			PreparedStatement statementInsertVersion = this.database.getStatement(connection, INSERT_NEW_VERSION, argsNV);

			statementSetFalse.executeUpdate();
			statementInsertVersion.executeUpdate();

			ResultSet rs = statementInsertVersion.getGeneratedKeys();
			connection.commit();

			if (rs.next()) {
				rule.setId(id);
				rule.setVersion(rs.getInt("version"));
				return true;
			} else {
				return false;
			}
		}
		catch (SQLException e ) {
			throw e;   
		}finally {
			connection.setAutoCommit(true);
			if (connection != null) {
				connection.close();
			}
		}
	}

	public ArrayList<Rule> getRuleVersions(int id) throws SQLException {
		ArrayList<Rule> out = new ArrayList<>();

		Connection connection = this.database.connection();

		try (PreparedStatement statement = this.database.getStatement(connection,SELECT_BY_ID_VERSIONS,id)) {
			ResultSet result = statement.executeQuery(); 
			while (result.next()) {
				Rule rule = this.readRow(result);
				out.add(rule);
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


	private Rule readRow (ResultSet result) throws SQLException {
		Rule rule = new Rule();
		rule.setId(result.getInt(1));
		rule.setVersion(result.getInt(2));
		rule.setName(result.getString(3));
		rule.setDescription(result.getString(4));
		rule.setLink(result.getString(5));
		rule.setQuery(result.getString(6));
		rule.setPredicate(result.getString(7));
		rule.setActivated(result.getBoolean(8));
		return rule;
	}



}

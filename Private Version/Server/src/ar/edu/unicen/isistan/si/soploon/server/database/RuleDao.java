package ar.edu.unicen.isistan.si.soploon.server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ar.edu.unicen.isistan.si.soploon.server.models.Rule;

public class RuleDao {

	private static final String TABLE_NAME = "soploon.rule";
	private static final String COLUMNS_INSERT = "(name,description,link,query,code,activated)";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS_INSERT + " VALUES";
	private static final String VALUES = "(?,?,?,?,?,?)";
	private static final String SINGLE_INSERT= INSERT+ " " + VALUES + ";";
	private static final String LAST_VERSION = "ORDER BY VERSION DESC LIMIT 1";
	private static final String CONDITION_ID = " WHERE id = ? ";
	private static final String CONDITION_ID_VERSION = " WHERE id = ? AND version = ? ";
	private static final String SET_FALSE_BY_ID = " UPDATE " + TABLE_NAME + " SET " + "activated = false" + CONDITION_ID + ";";
	private static final String SET_ACTIVATED_BY_ID = " UPDATE " + TABLE_NAME + " SET " + "activated = " + "?" + CONDITION_ID_VERSION + ";";
	private static final String SUB_QUERY_VERSION = " (SELECT version FROM " + TABLE_NAME  + CONDITION_ID + LAST_VERSION + ")";
	private static final String VALUES_NEW_VERSION = "(?, " + SUB_QUERY_VERSION + "+1," + "?,?,?,?,?,?)";
	private static final String INSERT_NEW_VERSION = " INSERT INTO " + TABLE_NAME + " VALUES " + VALUES_NEW_VERSION + ";";
	private static final String SIMPLE_SELECT = "SELECT * FROM " + TABLE_NAME ;
	private static final String SELECT_BY_ID = SIMPLE_SELECT + " " + CONDITION_ID + LAST_VERSION;
	private static final String SELECT_RULES = SIMPLE_SELECT + " ORDER BY id, version;";
	private static final String SELECT_BY_ID_VERSIONS = SIMPLE_SELECT + CONDITION_ID + ";";

	private Database database;

	public RuleDao(Database database) {
		this.database = database;
	}

	public boolean insert(Rule rule) throws SQLException {
		Object[] args = new Object[6];
		args[0] = rule.getName();
		args[1] = rule.getDescription();
		args[2]	= rule.getLink();
		args[3] = rule.getQuery();
		args[4] = rule.getCode();
		args[5] = rule.getActivated();

		try (Connection connection = this.database.connection();PreparedStatement statement = this.database.getStatement(connection,SINGLE_INSERT,args)) {
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
		} 
	}

	public Rule getRule(int id) throws SQLException {

		try (Connection connection = this.database.connection();PreparedStatement statement = this.database.getStatement(connection,SELECT_BY_ID,id)) {
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				Rule rule = this.readRow(result);
				return rule;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList<Rule> getRules() throws SQLException {
		ArrayList<Rule> out = new ArrayList<>();

		try (Connection connection = this.database.connection();PreparedStatement statement = this.database.getStatement(connection,SELECT_RULES)) {
			ResultSet result = statement.executeQuery(); 
			while (result.next()) {
				Rule rule = this.readRow(result);
				out.add(rule);
			}
			return out;
		}
		catch (SQLException e) {
			throw e;
		}
	}

	public boolean editRule (int id, Rule rule) throws SQLException {

		//SET FALSE
		Object [] argsSF = new Object [1];
		argsSF [0] = id;

		//SET ACTIVATED
		Object[] argsSA = new Object[3];
		argsSA[0] = rule.getActivated();
		argsSA[1] = rule.getId();
		argsSA[2] = rule.getVersion();

		Connection connection = this.database.connection();

		try {
			connection.setAutoCommit(false);

			PreparedStatement statementSetFalse = this.database.getStatement(connection,SET_FALSE_BY_ID,argsSF); 
			PreparedStatement statementInsertVersion = this.database.getStatement(connection, SET_ACTIVATED_BY_ID, argsSA);

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
			connection.close();	
		}
	}

	public ArrayList<Rule> getRuleVersions(int id) throws SQLException {
		ArrayList<Rule> out = new ArrayList<>();

		try (Connection connection = this.database.connection();PreparedStatement statement = this.database.getStatement(connection,SELECT_BY_ID_VERSIONS,id)) {
			ResultSet result = statement.executeQuery(); 
			while (result.next()) {
				Rule rule = this.readRow(result);
				out.add(rule);
			}
			return out;
		}
		catch (SQLException e) {
			throw e;
		}
	}
	
	public boolean newVersion (int id, Rule rule) throws SQLException{
		//SET FALSE
				Object [] argsSF = new Object [1];
				argsSF [0] = id;

				//NEW VERSION
				Object[] argsNV = new Object[8];
				argsNV[0] = id;
				argsNV[1] = id;
				argsNV[2] = rule.getName();
				argsNV[3] = rule.getDescription();
				argsNV[4] = rule.getLink();
				argsNV[5] = rule.getQuery();
				argsNV[6] = rule.getCode();
				argsNV[7] = true;	

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
					connection.close();
					
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
		rule.setCode(result.getString(7));
		rule.setActivated(result.getBoolean(8));
		return rule;
	}



}

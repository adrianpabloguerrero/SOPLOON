package isistan.soploon.rule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;


import isistan.soploon.database.Database;

public class RuleDao {

	private static final String TABLE_NAME = "soploon.rule";
	private static final String COLUMNS = "(name,description,link,query,predicate,activated,version)";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS + " VALUES";
	private static final String VALUES = "(?,?,?,?,?,?,?)";
	private static final String SINGLE_INSERT= INSERT+ " " + VALUES + ";";
	private static final String CONDITION_ID = "WHERE id = ? ";
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " " + CONDITION_ID;
	private static final String MODIFY = "UPDATE " + TABLE_NAME + " SET " + "name = ? , description = ? ,link = ?, query = ? , predicate = ? , activated = ? , version = ? " + CONDITION_ID;

	private Database database;
	
	public RuleDao(Database database) {
		this.database = database;
	}

	public boolean insert(Rule rule) {
		
		Object[] args = new Object[7];
		args[0] = rule.getName();
		args[1] = rule.getDescription();
		args[2]	= rule.getLink();
		args[3] = rule.getQuery();
		args[4] = rule.getPredicate();
		args[5] = rule.getActivated();
		args[6] = rule.getVersion();
		
		return this.database.insert(SINGLE_INSERT, args) == 1;
	}
	
	public Rule getRuleById (int id) {
		try (PreparedStatement statement = this.database.getStatement(SELECT_BY_ID,id)) {
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				Rule rule = new Rule();
				rule.setId(result.getInt(1));
				rule.setName(result.getString(2));
				rule.setDescription(result.getString(3));
				rule.setLink(result.getString(4));
				rule.setQuery(result.getString(5));
				rule.setPredicate(result.getString(6));
				rule.setActivated(result.getBoolean(7));
				rule.setVersion(result.getString(8));
				return rule;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateRule (int id, Rule rule) {
		
		Object[] args = new Object[8];
		args[0] = rule.getName();
		args[1] = rule.getDescription();
		args[2]	= rule.getLink();
		args[3] = rule.getQuery();
		args[4] = rule.getPredicate();
		args[5] = rule.getActivated();
		args[6] = rule.getVersion();
		args[7] = id;
		
		return this.database.insert(MODIFY, args)==1;
	
	}
	
}

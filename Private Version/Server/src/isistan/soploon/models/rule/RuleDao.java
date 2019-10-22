package isistan.soploon.models.rule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import isistan.soploon.database.Database;

public class RuleDao {

	private static final String TABLE_NAME = "soploon.rule";
	private static final String COLUMNS = "(version,name,description,link,query,predicate,activated)";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + COLUMNS + " VALUES";
	private static final String VALUES = "(?,?,?,?,?,?,?)";
	private static final String SINGLE_INSERT= INSERT+ " " + VALUES + ";";
	private static final String CONDITION_ID = "WHERE id = ? ";
	// TODO El SELECT by id deberia devolver la última versión de la regla
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " " + CONDITION_ID;
	// TODO El UPDATE no existiria, siempre se crearía una nueva fila en la tabla, incrementando el valor de la version en 1
	private static final String MODIFY = "UPDATE " + TABLE_NAME + " SET " + "name = ? , description = ? ,link = ?, query = ? , predicate = ? , activated = ? , version = ? " + CONDITION_ID;

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
		
		try (PreparedStatement statement = this.database.getStatement(SINGLE_INSERT,args)) {
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
		try (PreparedStatement statement = this.database.getStatement(SELECT_BY_ID,id)) {
			ResultSet result = statement.executeQuery();
			if (result.next()) {
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
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList<Rule> getRules() {
		ArrayList<Rule> out = new ArrayList<>();
		// TODO Obtener todas las reglas activadas y con la ultima version (las versiones previas deberian estar desactivadas)
		return out;
	}
	
	
	
	// Viru: Lo dejo aca por si sirve de referencia
	/*
	 * 
	public boolean insert(List<Activity> activities) {
		Gson gson = new Gson();
		
		String query = INSERT;
		Object[] args = new Object[4*activities.size()];

		int index = 0;
		for (Activity activity: activities) {
			args[index+0] = activity.getId();
			args[index+1] = activity.getTime();
			args[index+2] = activity.getElapsedTime();
			args[index+3] = gson.toJson(activity.getActivities());
			if (index != 0)
				query += ", " + VALUES;
			else 
				query += " " + VALUES;
			index += 4;
		}
		
		query += ";";
		
		return this.database.insert(query, args) == activities.size();
	}
	 */
	
	
}

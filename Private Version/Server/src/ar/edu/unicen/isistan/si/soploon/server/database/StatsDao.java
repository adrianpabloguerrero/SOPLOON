package ar.edu.unicen.isistan.si.soploon.server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ar.edu.unicen.isistan.si.soploon.server.models.ErrorRateElement;
import ar.edu.unicen.isistan.si.soploon.server.models.ErrorWS;

public class StatsDao {

	private static final String TABLE_CORRECTION = " soploon.correction ";
	private static final String TABLE_ERROR = " soploon.error ";
	private static final String VIEW_COMPLETE_ERROR = " soploon.complete_error ";
	private static final String SELECT_COUNT_USER = "SELECT COUNT (DISTINCT id_user) FROM ";
	private static final String SELECT_COUNT_PROJECT = "SELECT COUNT (DISTINCT id_project) FROM ";
	private static final String SELECT_COUNT = "SELECT COUNT (*) FROM ";
	private static final String CONDITION_DATE = " date BETWEEN ? AND ? ";
	private static final String SELECT_COUNT_USERS_BETWEEN_DATES = SELECT_COUNT_USER + TABLE_CORRECTION + " WHERE " + CONDITION_DATE;
	private static final String SELECT_COUNT_PROJECTS_BETWEEN_DATES = SELECT_COUNT_PROJECT + TABLE_CORRECTION + " WHERE " + CONDITION_DATE;
	private static final String SELECT_COUNT_CORRECTIONS_BETWEEN_DATES = SELECT_COUNT + TABLE_CORRECTION + " WHERE " + CONDITION_DATE;
	private static final String SELECT_COUNT_ERRORS_BETWEEN_DATES = SELECT_COUNT + TABLE_ERROR + " WHERE " + CONDITION_DATE;
	private static final String SELECT_RATE_ERRORS_BETWEEN_DATES = "SELECT  rule_name, COUNT (*) * 100 /SUM(COUNT(*)) OVER() FROM " + VIEW_COMPLETE_ERROR + " WHERE " + CONDITION_DATE +  "GROUP BY  rule_name";
	private static final String SELECT_TOP_ERRORS_BETWEEN_DATES = "SELECT rule_name, COUNT (*) FROM " + VIEW_COMPLETE_ERROR + " WHERE " + CONDITION_DATE + "GROUP BY (id_rule, rule_name) ORDER BY (COUNT (*)) DESC LIMIT 5" ;

	private Database database;
	
	public StatsDao(Database database) {
		this.database = database;
	}

	public int getUsersQuantity(Long dateStart, Long dateEnd) throws SQLException {
		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_COUNT_USERS_BETWEEN_DATES, dateStart, dateEnd)) {
			ResultSet result = statement.executeQuery();
			if (result.next())
				return result.getInt(1);
			else
				return 0;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public int getProjectsQuantity(Long dateStart, Long dateEnd) throws SQLException {
		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_COUNT_PROJECTS_BETWEEN_DATES, dateStart, dateEnd)) {
			ResultSet result = statement.executeQuery();
			if (result.next())
				return result.getInt(1);
			else
				return 0;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	
	public int getErrorsQuantity(Long dateStart, Long dateEnd) throws SQLException {
		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_COUNT_ERRORS_BETWEEN_DATES, dateStart, dateEnd)) {
			ResultSet result = statement.executeQuery();
			if (result.next())
				return result.getInt(1);
			else
				return 0;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	
	public int getCorrectionsQuantity (Long dateStart, Long dateEnd) throws SQLException {
		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_COUNT_CORRECTIONS_BETWEEN_DATES, dateStart, dateEnd)) {
			ResultSet result = statement.executeQuery();
			if (result.next())
				return result.getInt(1);
			else
				return 0;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public ArrayList<ErrorRateElement> getErrorsRateElement(Long dateStart, Long dateEnd) throws SQLException {
		ArrayList<ErrorRateElement> out = new ArrayList<>();
		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_RATE_ERRORS_BETWEEN_DATES, dateStart, dateEnd)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ErrorRateElement errorRateElement = new ErrorRateElement();
				errorRateElement.setName(result.getString(1));
				errorRateElement.setY(result.getFloat(2));
				out.add(errorRateElement);
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

	public ArrayList<ErrorRateElement> getErrosTopFive(Long dateStart, Long dateEnd) throws SQLException {
		ArrayList<ErrorRateElement> out = new ArrayList<>();
		Connection connection = this.database.connection();
		try (PreparedStatement statement = this.database.getStatement(connection, SELECT_TOP_ERRORS_BETWEEN_DATES, dateStart, dateEnd)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ErrorRateElement errorRateElement = new ErrorRateElement();
				errorRateElement.setName(result.getString(1));
				errorRateElement.setY(result.getFloat(2));
				out.add(errorRateElement);
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

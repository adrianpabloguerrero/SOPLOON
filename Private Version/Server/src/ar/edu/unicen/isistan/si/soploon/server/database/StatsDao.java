package ar.edu.unicen.isistan.si.soploon.server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ar.edu.unicen.isistan.si.soploon.server.models.ErrorStatsElement;
import ar.edu.unicen.isistan.si.soploon.server.models.LastUse;

public class StatsDao {

	private static final String TABLE_CORRECTION = " soploon.correction ";
	private static final String TABLE_PROJECT = " soploon.project ";
	private static final String TABLE_ERROR = " soploon.error ";
	private static final String TABLE_USER = " soploon.user ";
	private static final String VIEW_COMPLETE_ERROR = " soploon.complete_error ";
	private static final String SELECT_COUNT_USER = "SELECT COUNT (DISTINCT id_user) FROM ";
	private static final String SELECT_COUNT_PROJECT = "SELECT COUNT (DISTINCT(id_project,id_user)) FROM ";
	private static final String SELECT_COUNT = "SELECT COUNT (*) FROM ";
	private static final String CONDITION_DATE = " date BETWEEN ? AND ? ";
	private static final String CONDITION_ID = " id_project = ? AND id_user = ?  AND date=? ";
	private static final String SELECT_COUNT_USERS_BETWEEN_DATES = SELECT_COUNT_USER + TABLE_CORRECTION + " WHERE "
			+ CONDITION_DATE;
	private static final String SELECT_COUNT_PROJECTS_BETWEEN_DATES = SELECT_COUNT_PROJECT + TABLE_CORRECTION
			+ " WHERE " + CONDITION_DATE;
	private static final String SELECT_COUNT_CORRECTIONS_BETWEEN_DATES = SELECT_COUNT + TABLE_CORRECTION + " WHERE "
			+ CONDITION_DATE;
	private static final String SELECT_COUNT_ERRORS_BETWEEN_DATES = SELECT_COUNT + TABLE_ERROR + " WHERE "
			+ CONDITION_DATE;
	private static final String SELECT_RATE_ERRORS_BETWEEN_DATES = "SELECT  rule_name, COUNT (*) * 100 /SUM(COUNT(*)) OVER() FROM "
			+ VIEW_COMPLETE_ERROR + " WHERE " + CONDITION_DATE + "GROUP BY  rule_name";
	private static final String SELECT_TOP_ERRORS_BETWEEN_DATES = "SELECT rule_name, COUNT (*) FROM "
			+ VIEW_COMPLETE_ERROR + " WHERE " + CONDITION_DATE
			+ "GROUP BY (id_rule, rule_name) ORDER BY (COUNT (*)) DESC LIMIT 5";
	private static final String SELECT_ACUM_CORRECTIONS_BETWEEN_DATES = "SELECT DATE_TRUNC('day',to_timestamp(date/1000))::DATE,COUNT(*) "
			+ " FROM " + TABLE_CORRECTION + " WHERE " + CONDITION_DATE
			+ " GROUP BY (date_trunc('day',to_timestamp(date/1000))) ORDER BY (date_trunc('day',to_timestamp(date/1000))) ";
	private static final String SELECT_LAST_CORRECTION = "SELECT date, id_project, id_user FROM " + TABLE_CORRECTION
			+ " ORDER BY date DESC LIMIT 1 ";
	private static final String SELECT_USER_LAST_CORRECTION = "SELECT name FROM " + TABLE_USER + " WHERE id = ? ";
	private static final String SELECT_PROJECT_LAST_CORRECTION = "SELECT name FROM " + TABLE_PROJECT
			+ " WHERE id = ? AND id_user = ?";
	private static final String SELECT_ERRORS_LAST_CORRECTION = "SELECT  rule_name, COUNT (*) FROM "
			+ VIEW_COMPLETE_ERROR + " WHERE " + CONDITION_ID + "GROUP BY  rule_name" + " ORDER BY COUNT(*) DESC";

	private Database database;

	public StatsDao(Database database) {
		this.database = database;
	}

	public int getUsersQuantity(Long dateStart, Long dateEnd) throws SQLException {
		try (Connection connection = this.database.getConnection();
				PreparedStatement statement = this.database.getStatement(connection, SELECT_COUNT_USERS_BETWEEN_DATES,
						dateStart, dateEnd)) {
			ResultSet result = statement.executeQuery();
			if (result.next())
				return result.getInt(1);
			else
				return 0;
		} catch (SQLException e) {
			throw e;
		}
	}

	public int getProjectsQuantity(Long dateStart, Long dateEnd) throws SQLException {
		try (Connection connection = this.database.getConnection();
				PreparedStatement statement = this.database.getStatement(connection,
						SELECT_COUNT_PROJECTS_BETWEEN_DATES, dateStart, dateEnd)) {
			ResultSet result = statement.executeQuery();
			if (result.next())
				return result.getInt(1);
			else
				return 0;
		} catch (SQLException e) {
			throw e;
		}
	}

	public int getErrorsQuantity(Long dateStart, Long dateEnd) throws SQLException {
		try (Connection connection = this.database.getConnection();
				PreparedStatement statement = this.database.getStatement(connection, SELECT_COUNT_ERRORS_BETWEEN_DATES,
						dateStart, dateEnd)) {
			ResultSet result = statement.executeQuery();
			if (result.next())
				return result.getInt(1);
			else
				return 0;
		} catch (SQLException e) {
			throw e;
		}
	}

	public int getCorrectionsQuantity(Long dateStart, Long dateEnd) throws SQLException {
		try (Connection connection = this.database.getConnection();
				PreparedStatement statement = this.database.getStatement(connection,
						SELECT_COUNT_CORRECTIONS_BETWEEN_DATES, dateStart, dateEnd)) {
			ResultSet result = statement.executeQuery();
			if (result.next())
				return result.getInt(1);
			else
				return 0;
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList<ErrorStatsElement> getErrorsRateElement(Long dateStart, Long dateEnd) throws SQLException {
		ArrayList<ErrorStatsElement> out = new ArrayList<>();
		try (Connection connection = this.database.getConnection();
				PreparedStatement statement = this.database.getStatement(connection, SELECT_RATE_ERRORS_BETWEEN_DATES,
						dateStart, dateEnd)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ErrorStatsElement errorRateElement = new ErrorStatsElement();
				errorRateElement.setName(result.getString(1));
				errorRateElement.setY(result.getFloat(2));
				out.add(errorRateElement);
			}
			return out;
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList<ErrorStatsElement> getErrosTopFive(Long dateStart, Long dateEnd) throws SQLException {
		ArrayList<ErrorStatsElement> out = new ArrayList<>();
		try (Connection connection = this.database.getConnection(); PreparedStatement statement = this.database.getStatement(connection, SELECT_TOP_ERRORS_BETWEEN_DATES,
						dateStart, dateEnd)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ErrorStatsElement errorRateElement = new ErrorStatsElement();
				errorRateElement.setName(result.getString(1));
				errorRateElement.setY(result.getFloat(2));
				out.add(errorRateElement);
			}
			return out;
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList<ErrorStatsElement> getAcumCorrections(Long dateStart, Long dateEnd) throws SQLException {
		ArrayList<ErrorStatsElement> out = new ArrayList<>();
		try (Connection connection = this.database.getConnection(); PreparedStatement statement = this.database.getStatement(connection,
						SELECT_ACUM_CORRECTIONS_BETWEEN_DATES, dateStart, dateEnd)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ErrorStatsElement errorRateElement = new ErrorStatsElement();
				errorRateElement.setName(result.getString(1));
				errorRateElement.setY(result.getFloat(2));
				out.add(errorRateElement);
			}
			return out;
		} catch (SQLException e) {
			throw e;
		}
	}

	public LastUse getLastUse() throws SQLException {
		LastUse lastUse = new LastUse();
		long date;
		int idUser;
		int idProject;
		try (Connection connection = this.database.getConnection()) {
			PreparedStatement infoStatement = this.database.getStatement(connection, SELECT_LAST_CORRECTION);
			ResultSet rsInfo = infoStatement.executeQuery();
			if (rsInfo.next()) {
				date = rsInfo.getLong(1);
				idProject = rsInfo.getInt(2);
				idUser = rsInfo.getInt(3);
				lastUse.setDate(rsInfo.getLong(1));

				PreparedStatement userStatement = this.database.getStatement(connection, SELECT_USER_LAST_CORRECTION,
						idUser);
				ResultSet rsUser = userStatement.executeQuery();
				if (rsUser.next()) {
					lastUse.setNameUser(rsUser.getString(1));
				}

				PreparedStatement projectStatement = this.database.getStatement(connection,
						SELECT_PROJECT_LAST_CORRECTION, idProject, idUser);
				ResultSet rsProject = projectStatement.executeQuery();
				if (rsProject.next()) {
					lastUse.setNameProject(rsProject.getString(1));
				}

				PreparedStatement errorsStatement = this.database.getStatement(connection,
						SELECT_ERRORS_LAST_CORRECTION, idProject, idUser, date);
				ResultSet rsErrors = errorsStatement.executeQuery();
				ArrayList<ErrorStatsElement> listErrors = new ArrayList<ErrorStatsElement>();
				while (rsErrors.next()) {
					ErrorStatsElement errorRateElement = new ErrorStatsElement();
					errorRateElement.setName(rsErrors.getString(1));
					errorRateElement.setY(rsErrors.getFloat(2));
					listErrors.add(errorRateElement);
				}
				lastUse.setErrors(listErrors);
			}
		} catch (SQLException e) {
			throw e;
		}
		return lastUse;
	}

}

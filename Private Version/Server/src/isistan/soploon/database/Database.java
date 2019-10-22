package isistan.soploon.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

	private String path;
	private String user;
	private String pass;
	private Connection connection;
	
	public Database(String path,String user, String pass) {
		this.path = path;
		this.user = user;
		this.pass = pass;
		this.connection = null;
	}
	
	public boolean connect() {
			try {
				if (this.connection == null || this.connection.isClosed()) {
					this.connection = DriverManager.getConnection(this.path,this.user,this.pass);
					return true;
				} else {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		
	}

	public void disconnect() {
		try {
			if (this.connection != null) {
				this.connection.close();
			}
		} catch (SQLException e) {
			
		}
	}
	
	public Connection connection() {
		if (this.connect())
			return this.connection;
		else
			return null;
	}
	
	public boolean initialize(String initialize_query) {
		if (this.connect()) {
			try {
				return this.connection.createStatement().execute(initialize_query);
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}
		
	public int insert(String insertQuery, Object... args) {
		if (this.connect()) {
			try (PreparedStatement statement = this.connection.prepareStatement(insertQuery);) {
				if (args != null)
					for (int index = 0; index < args.length; index++)
						statement.setObject(index+1, args[index]);
				statement.getGeneratedKeys();
				return statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	
	

	public PreparedStatement getStatement(String query, Object... args) {
		if (this.connect()) {
			PreparedStatement statement = null;
			try {
				statement = this.connection.prepareStatement(query);
				if (args != null) {
					for (int index = 0; index < args.length; index++)
						statement.setObject(index+1, args[index]);
				}
				return statement;
			} catch (Exception e) {
				e.printStackTrace();
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				return null;
			}
		} else
			return null;
	}

	
}

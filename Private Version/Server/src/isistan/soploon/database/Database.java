package isistan.soploon.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbcp2.BasicDataSource;

public class Database {

	private String path;
	private String user;
	private String pass;
	private BasicDataSource dataSource;
	
	public Database(String path,String user, String pass) {
		this.path = path;
		this.user = user;
		this.pass = pass;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.dataSource = new BasicDataSource();
		this.dataSource.setUrl(this.path);
		this.dataSource.setUsername(this.user);
		this.dataSource.setPassword(this.pass);
		this.dataSource.setMinIdle(5);
		this.dataSource.setMaxIdle(10);
		this.dataSource.setMaxOpenPreparedStatements(100);
	}
	
	public boolean connect() {
		return !this.dataSource.isClosed();		
	}

	public void disconnect() {
		try {
			this.dataSource.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection connection() {
		try {
			if (this.connect())
				return this.dataSource.getConnection();
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
			
	
	
	public PreparedStatement getStatement(Connection connection, String query, Object... args) {
		if (this.connect()) {
			PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
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

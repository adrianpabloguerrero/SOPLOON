import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import isistan.soploon.database.Database;

public class Main {

	static final String URL = "jdbc:postgresql://192.168.2.101:5432/soploon";
	static final String USER = "soploon_admin";
	static final String PASS = "soploononon";
	
	public static void main(String[] args) {
		
		
		Database database = new Database(URL,USER,PASS);
		
		database.connect();
		
		Connection c = database.connection();
		try {
			Statement s = c.createStatement();
			ResultSet res = s.executeQuery("SELECT * FROM soploon.rule");
			
			while (res.next()) {
				System.out.println(res.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		database.disconnect();
	}

}

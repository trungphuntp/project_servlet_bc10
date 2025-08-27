package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConfig {
	public static Connection getConnection() {
		Connection connection = null;
		try {
			String url = "jdbc:mysql://localhost:3307/crm_app";
			String root = "root";
			String password = "admin123";
			
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    return DriverManager.getConnection(url, root, password);
			
		} catch (Exception e) {
			System.out.println("MySQLConfig : " + e.getMessage());
		}
		return connection;
	}
	
}

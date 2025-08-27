package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Users;

public class AuthenticationRepository {
	public List<Users> findUser(String email, String password) {
		String query = "SELECT *\r\n"
				+ "FROM users u\r\n"
				+ "WHERE u.email=? AND u.password=? ;";
		
		Connection connection = MySQLConfig.getConnection();
		List<Users> listUsers = new ArrayList<Users>();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1	, email);
			preparedStatement.setString(2	, password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Users users = new Users();
				users.setId(resultSet.getInt("id"));
				users.setEmail(resultSet.getString("email"));
				users.setPassword(resultSet.getString("password"));
				users.setFullname(resultSet.getString("fullname"));
				users.setRole_id(resultSet.getInt("role_id"));
				
				listUsers.add(users);
			}
			

		} catch (Exception e) {
			System.out.println("LoginRepository : " + e.getMessage());
		}
		
		return listUsers;
		
	}
}

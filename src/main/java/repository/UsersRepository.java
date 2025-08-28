package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Users;

public class UsersRepository {
	public List<Users> findAllUsers() {
		String query = "SELECT * \r\n"
				+ "FROM users u\r\n"
				+ "JOIN roles r \r\n"
				+ "ON u.role_id = r.id;";
		Connection connection = MySQLConfig.getConnection();
		List<Users> listUsers = new ArrayList<Users>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Users users = new Users();
				users.setId(resultSet.getInt("id"));
				users.setEmail(resultSet.getString("email"));
				users.setPassword(resultSet.getString("password"));
				users.setFullname(resultSet.getString("fullname"));
				users.setRole_id(resultSet.getInt("role_id"));
				users.setRole_desc(resultSet.getString("description"));
				listUsers.add(users);
			}
			
		} catch (Exception e) {
			System.out.println("UsersRepository : " +  e.getMessage());
		}
		
		return listUsers;
	}
	
	public void name() {
//		String query = ""
	}
}

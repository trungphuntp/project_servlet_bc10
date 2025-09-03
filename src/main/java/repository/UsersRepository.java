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
				users.setPhone(resultSet.getString("phone"));
				users.setAvatar(resultSet.getString("avatar"));
				listUsers.add(users);
			}
			
		} catch (Exception e) {
			System.out.println("UsersRepository : " +  e.getMessage());
		}
		
		return listUsers;
	}
	
	public int insertUser(String fullname, String email, String password, String phone, int rolesId) {
		String query = "INSERT INTO users( email, password,fullname, avatar,role_id,phone )\r\n"
				+ "VALUES (? , ?, ?, ?, ?, ?);";
		int rowCount = 0;
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, fullname);
			preparedStatement.setString(4, "");
			preparedStatement.setInt(5, rolesId);
			preparedStatement.setString(6, phone);
			rowCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("UsersRepository : " +  e.getMessage());
		}
		
		return rowCount;
	}
	
	public int deleteUser(int id) {
		String query = "DELETE FROM users s\r\n"
				+ "WHERE s.id = ?;";
		int rowCount = 0;
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			 
			rowCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("UsersRepository : " +  e.getMessage());
		}
		
		
		return rowCount;
	}
	
	public Users findUsersById(int id) {
		String query = "SELECT * FROM users u WHERE u.id = ?; \r\n";
		Users users = new Users();
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				users.setId(resultSet.getInt("id"));
				users.setEmail(resultSet.getString("email"));
				users.setPassword(resultSet.getString("password"));
				users.setFullname(resultSet.getString("fullname"));
				users.setRole_id(resultSet.getInt("role_id"));
				users.setPhone(resultSet.getString("phone"));
			}
		} catch (Exception e) {
			System.out.println("UsersRepository : " +  e.getMessage());
		}
		return users;
	}
	
	public Users findUsersByIdEmailPassword(int id, String email, String password) {
		String query = "SELECT * FROM users u WHERE u.email= ? AND u.password= ? AND u.id = ?";
		Users users = new Users();
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.setInt(3, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				users.setId(resultSet.getInt("id"));
				users.setEmail(resultSet.getString("email"));
				users.setPassword(resultSet.getString("password"));
				users.setFullname(resultSet.getString("fullname"));
				users.setRole_id(resultSet.getInt("role_id"));
				users.setPhone(resultSet.getString("phone"));
				users.setAvatar(resultSet.getString("avatar"));
			}
		} catch (Exception e) {
			System.out.println("UsersRepository : " +  e.getMessage());
		}
		
		return users;
	}
	
	public int updateUser(String fullname, String email, String password, String phone, int roleId, int idEdit) {
		int rowCount = 0;
		String query = "UPDATE users\r\n"
				+ "SET email = ? ,\r\n"
				+ "    password = ?,\r\n"
				+ "    fullname = ?,\r\n"
				+ "    role_id = ?,\r\n"
				+ "    phone = ?\r\n"
				+ "WHERE id = ?;";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, fullname);
			preparedStatement.setInt(4, roleId);
			preparedStatement.setString(5, phone);
			preparedStatement.setInt(6, idEdit);
			rowCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("UsersRepository : " +  e.getMessage());
		}
		
		
		return rowCount;
	}
}

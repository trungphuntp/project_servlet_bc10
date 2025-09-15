package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Roles;

public class RolesRepository {
	public List<Roles> findAllRoles() {
		String query = "SELECT *\r\n"
				+ "FROM roles r;";
		Connection connection = MySQLConfig.getConnection();
		List<Roles> listRoles = new ArrayList<Roles>();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Roles roles = new Roles();
				roles.setId(resultSet.getInt("id"));
				roles.setName(resultSet.getString("name"));
				roles.setDesc(resultSet.getString("description"));
				
				listRoles.add(roles);
			}
			
		} catch (Exception e) {
			System.out.println("RolesRepository : " + e.getMessage());
		}
		return listRoles;
	}
	
	public List<Roles> findAllRolesExceptAdmin() {
		String query = "SELECT * FROM roles r WHERE r.id != 1;\r\n";
		
		Connection connection = MySQLConfig.getConnection();
		List<Roles> listRoles = new ArrayList<Roles>();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Roles roles = new Roles();
				roles.setId(resultSet.getInt("id"));
				roles.setName(resultSet.getString("name"));
				roles.setDesc(resultSet.getString("description"));
				
				listRoles.add(roles);
			}
			
		} catch (Exception e) {
			System.out.println("RolesRepository findAllRolesExceptAdmin : " + e.getMessage());
		}
		return listRoles;
	}
	
	public int insertRole(String name, String desc) {
		String query = "INSERT INTO roles ( name, description) VALUES\r\n"
				+ "( ? , ?);";
		
		int rowCount = 0;
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, desc);
			
			rowCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("RolesRepository : " + e.getMessage());
		}
		return rowCount;
	}
	
	public int deleteRole(int id) {
		int rowCount = 0;
		String query = "DELETE FROM roles r WHERE id = ?;";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,id);
			
			rowCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("RolesRepository : " + e.getMessage());
		}
		return rowCount;
		
	}
	
	public int updateRole(String name, String desc, int id ) {
		int rowCount = 0;
		String query = "UPDATE roles\r\n"
				+ "SET name = ?, description = ?\r\n"
				+ "WHERE id = ?;";
		Connection connection  = MySQLConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, desc);
			preparedStatement.setInt(3, id);
			
			rowCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("RolesRepository : " + e.getMessage());
		}
		
		return rowCount;
	}
	
	public Roles findRoleById(int id) {
		String query = "SELECT * FROM roles r WHERE id = ?;";
		Connection connection = MySQLConfig.getConnection();
		Roles roles = new Roles();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				roles.setId(resultSet.getInt("id"));
				roles.setName(resultSet.getString("name"));
				roles.setDesc(resultSet.getString("description"));
			}
			
		} catch (Exception e) {
			System.out.println("RolesRepository : " + e.getMessage());
		}
		return roles;
	}
}

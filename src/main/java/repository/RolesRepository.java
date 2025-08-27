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
}

package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Projects;

public class ProjectsRepository {
	public List<Projects> findAllProjects() {
		String query = "SELECT *\r\n"
				+ "FROM jobs j;";
		
		Connection connection = MySQLConfig.getConnection();
		List<Projects> listProject = new ArrayList<Projects>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Projects projects = new Projects();
				projects.setId(resultSet.getInt("id"));
				projects.setName(resultSet.getString("name"));
				projects.setDateStart(resultSet.getDate("start_date"));
				projects.setDateEnd(resultSet.getDate("end_date"));
				listProject.add(projects);
			}
		
		} catch (Exception e) {
			System.out.println("ProjectsRepository : " + e.getMessage());
		}
		return listProject;
	} 
}

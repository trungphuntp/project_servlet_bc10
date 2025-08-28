package repository;

import java.sql.Connection;
import java.sql.Date;
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
	
	public int insertProject(String nameProject, Date startDate, Date endDate) {
		String query = "INSERT INTO jobs ( name, start_date, end_date) VALUES\r\n"
				+ "(? , ?, ?);";
		int rowCount = 0;
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, nameProject);
			preparedStatement.setDate(2, startDate);
			preparedStatement.setDate(3, endDate);
			rowCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("ProjectsRepository : " + e.getMessage());
		}
		
		return rowCount;
	}
	
	public int deleteProject(int id) {
		int rowCount = 0;
		String query = "DELETE FROM jobs j\r\n"
				+ "WHERE j.id = ?;";
		
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			
			rowCount = preparedStatement.executeUpdate();
		}  catch (Exception e) {
			System.out.println("ProjectsRepository : " + e.getMessage());
		}
		
		return rowCount;
	}
	
	public Projects findProject(int id) {
		String query = "SELECT * FROM jobs j WHERE j.id = ?;";
		Projects projects = new Projects();
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				projects.setId(resultSet.getInt("id"));
				projects.setName(resultSet.getString("name"));
				projects.setDateStart(resultSet.getDate("start_date"));
				projects.setDateEnd(resultSet.getDate("end_date"));
			}
			
		} catch (Exception e) {
			System.out.println("ProjectsRepository : " + e.getMessage());
		}
		
		return projects;
	}
	
	public int updateProjectById(String name, Date startDate, Date endDate, int id) {
		int rowCount = 0;
		String query = "UPDATE jobs j\r\n"
				+ "SET name = ?, start_date= ?, end_date = ?\r\n"
				+ "WHERE j.id = ?;";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setDate(2, startDate);
			preparedStatement.setDate(3, endDate);
			preparedStatement.setInt(4, id);
			rowCount = preparedStatement.executeUpdate();
		}	catch (Exception e) {
			System.out.println("ProjectsRepository : " + e.getMessage());
		}
		
		return rowCount;
	}
}

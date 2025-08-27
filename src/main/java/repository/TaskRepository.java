package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Tasks;

public class TaskRepository {
	public List<Tasks> findAllTasks() {
		String query = "SELECT t.id, t.name,t.start_date, t.end_date, t.user_id, t.job_id, t.status_id, j.name as nameJob, u.fullname as nameUser, s.name as nameStatus\r\n"
				+ "FROM tasks t\r\n"
				+ "JOIN jobs j ON t.job_id = j.id\r\n"
				+ "JOIN users u ON u.id = t.user_id\r\n"
				+ "JOIN status s ON s.id = t.status_id;";
		Connection connection = MySQLConfig.getConnection();
		List<Tasks> listTasks = new ArrayList<Tasks>();
		
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Tasks tasks = new Tasks();
				tasks.setId(resultSet.getInt("id"));
				tasks.setName(resultSet.getString("name"));
				tasks.setStartDate(resultSet.getDate("start_date"));
				tasks.setEndDate(resultSet.getDate("end_date"));
				tasks.setUser_id(resultSet.getInt("user_id"));
				tasks.setJob_id(resultSet.getInt("job_id"));
				tasks.setStatus_id(resultSet.getInt("status_id"));
				tasks.setNameUser(resultSet.getString("nameUser"));
				tasks.setJobName(resultSet.getString("nameJob"));
				tasks.setNameStatus(resultSet.getString("nameStatus"));
				listTasks.add(tasks);
			}
			
		} catch (Exception e) {
			System.out.println("TaskRepository : " + e.getMessage());
		}
		
		return  listTasks;
	}
}

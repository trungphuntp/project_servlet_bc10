package repository;

import java.sql.Connection;
import java.sql.Date;
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

	public int insertTask(String nameTask, int jobId, int userId, Date startDate, Date endDate) {
		int rowCount = 0;
		
		// id status = 1 => "đang thực hiện"
		int statusId = 1;
		String query = "INSERT INTO tasks( name, job_id,user_id, start_date,end_date, status_id )\r\n"
				+ "VALUES (? , ?, ?, ?, ?,?);;";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, nameTask);
			preparedStatement.setInt(2,	 jobId);
			preparedStatement.setInt(3, userId);
			preparedStatement.setDate(4, startDate);
			preparedStatement.setDate(5, endDate);
			preparedStatement.setInt(6, statusId);
			rowCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("TaskRepository : " + e.getMessage());
		}
		
		return rowCount;
	}
	
	public int deleteTask(int id) {
		int rowCount = 0;
		String query = "DELETE FROM tasks t WHERE t.id = ?;";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			rowCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("TaskRepository : " + e.getMessage());
		}
		
		return rowCount;
	}
	
	public Tasks findTaskById(int id) {
		Tasks tasks = new Tasks();
		String query = "SELECT t.id, t.name,t.start_date, t.end_date, t.user_id, t.job_id, t.status_id, j.name as nameJob, u.fullname as nameUser, s.name as nameStatus\r\n"
				+ "FROM tasks t\r\n"
				+ "JOIN jobs j ON t.job_id = j.id\r\n"
				+ "JOIN users u ON u.id = t.user_id\r\n"
				+ "JOIN status s ON s.id = t.status_id\r\n"
				+ "WHERE t.id = ?;";		
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
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
			}
		} catch (Exception e) {
			System.out.println("TaskRepository : " + e.getMessage());
		}
		return tasks;
	}
	
	public int updateTaskById(String name, Date startDate, Date endDate, int userId, int jobId,int statusId, int editId) {
		int rowCount = 0;
		String query = "UPDATE tasks\r\n"
				+ "SET name = ?,\r\n"
				+ "    start_date = ?,\r\n"
				+ "    end_date = ?,\r\n"
				+ "    user_id = ?,\r\n"
				+ "    job_id = ?,\r\n"
				+ "    status_id = ?\r\n"
				+ "WHERE id = ?;";
		Connection connection = MySQLConfig.getConnection();
		try {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, name);
		preparedStatement.setDate(2, startDate);
		preparedStatement.setDate(3, endDate);
		preparedStatement.setInt(4, userId);
		preparedStatement.setInt(5, jobId);
		preparedStatement.setInt(6, statusId);
		preparedStatement.setInt(7, editId);
		rowCount = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("TaskRepository : " + e.getMessage());
		}
		
		return rowCount;
		
	}
	
}

package services;

import java.sql.Date;
import java.util.List;

import entity.Tasks;
import repository.TaskRepository;

public class TasksServices {
	private TaskRepository taskRepository = new TaskRepository();
	
	public List<Tasks> getAllTasks() {
		return taskRepository.findAllTasks();
	}
	
	public int addTask(String nameTask, int jobId, int userId, String startDate, String endDate) {
		Date startDateSql = Date.valueOf(startDate);
		Date endDateSql = null;
		if (!endDate.isEmpty()) {
			endDateSql = Date.valueOf(endDate);
		}
		return taskRepository.insertTask(nameTask, jobId, userId, startDateSql, endDateSql);
	}
	
	public int deleteTask(int id) {
		return taskRepository.deleteTask(id);
	}
	
	public Tasks getTaskById(int id) {
		return taskRepository.findTaskById(id);
	}
	
	public int editTask(String name, String startDate, String endDate, int userId, int jobId,int statusId, int editIdt) {
		Date startDateSql = Date.valueOf(startDate);
		Date endDateSql = null;
		if (!endDate.isEmpty()) {
			endDateSql = Date.valueOf(endDate);
		}
		return taskRepository.updateTaskById(name, startDateSql, endDateSql, userId, jobId, statusId, editIdt);
	}
	
	public List<Tasks> getTaskByIdUser(int id) {
		return taskRepository.findTaskByIdUser(id);
	}
	
	public Tasks getTaskByIdAndIdUser(int id, int idUser) {
		return .taskRepository.findTaskByIdAndIdUser(id, idUser);
	}
	
}

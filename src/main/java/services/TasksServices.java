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
	
	public int addTask(String nameTask, int jobId, int userId, Date startDate, Date endDate) {
		return taskRepository.insertTask(nameTask, jobId, userId, startDate, endDate);
	}
	
	public int deleteTask(int id) {
		return taskRepository.deleteTask(id);
	}
	
	public Tasks getTaskById(int id) {
		return taskRepository.findTaskById(id);
	}
	
	public int editTask(String name, Date startDate, Date endDate, int userId, int jobId,int statusId, int editIdt) {
		return taskRepository.updateTaskById(name, startDate, endDate, userId, jobId, statusId, editIdt);
	}
	
}

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
	
	public int editTask(String name, String startDate, String endDate, int userId, int jobId,int statusId, int editId) {
		Date startDateSql = Date.valueOf(startDate);
		Date endDateSql = null;
		if (!endDate.isEmpty()) {
			endDateSql = Date.valueOf(endDate);
		}
		return taskRepository.updateTaskById(name, startDateSql, endDateSql, userId, jobId, statusId, editId);
	}
	
	public List<Tasks> getTaskByIdUser(int id) {
		return taskRepository.findTaskByIdUser(id);
	}
	
	public Tasks getTaskByIdAndIdUser(int id, int idUser) {
		return taskRepository.findTaskByIdAndIdUser(id, idUser);
	}
	
	public List<Tasks> getTasksByIdStatusAndIdUsers(int idStatus, int idUsers) {
		return taskRepository.findTasksByIdStatusAndIdUsers(idStatus, idUsers);
	} 
	
	public List<Tasks> getTasksByIdJob(int idJob){
		return taskRepository.findTasksByIdJob(idJob);
	}
	
	public List<Tasks> getTasksByIdStatusAndIdUserAndIdJob(int idStatus, int userId, int idJob ){
		return taskRepository.findTasksByIdStatusAndIdUsersAndIdJob(idStatus, userId, idJob);
	}
	
	public List<Tasks> getTasksByIdStatusAndIdJob(int idStatus, int idJob) {
		return taskRepository.findTasksByIdStatusAndIdJob(idStatus, idJob);
		
	}
}

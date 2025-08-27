package services;

import java.util.List;

import entity.Tasks;
import repository.TaskRepository;

public class TasksServices {
	private TaskRepository taskRepository = new TaskRepository();
	
	public List<Tasks> getAllTasks() {
		return taskRepository.findAllTasks();
	}
}

package services;

import java.sql.Date;
import java.util.List;

import entity.Projects;
import repository.ProjectsRepository;

public class ProjectsServices {
	private ProjectsRepository projectsRepository = new ProjectsRepository();
	public List<Projects> getAllProjects() {
		return projectsRepository.findAllProjects();	
	}
	
	public int addProject(String name, Date start, Date end) {
		return projectsRepository.insertProject(name, start, end);
	}
	
	public int removeProject(int id) {
		return projectsRepository.deleteProject(id);
	}
}

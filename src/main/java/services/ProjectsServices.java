package services;

import java.util.List;

import entity.Projects;
import repository.ProjectsRepository;

public class ProjectsServices {
	private ProjectsRepository projectsRepository = new ProjectsRepository();
	public List<Projects> getAllProjects() {
		return projectsRepository.findAllProjects();
		
	}
}

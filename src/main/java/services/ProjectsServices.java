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
	
	public int addProject(String name, String start, String end) {
		Date startDateSql = null;
		Date endDateSql = null;
		startDateSql =  Date.valueOf(start);
		if (!end.isEmpty()) {
			endDateSql =  Date.valueOf(end);
		}
		return projectsRepository.insertProject(name, startDateSql, endDateSql);
	}
	
	public int removeProject(int id) {
		return projectsRepository.deleteProject(id);
	}
	
	public Projects getProjectById(int id) {
		return projectsRepository.findProject(id);
	}
	
	public int editProjectById(String name,String start,String end, int  id) {
		Date startDateSql = null;
		Date endDateSql = null;
		startDateSql =  Date.valueOf(start);
		if (!end.isEmpty()) {
			endDateSql =  Date.valueOf(end);
		}
		return projectsRepository.updateProjectById(name, startDateSql, endDateSql, id);
	}
}

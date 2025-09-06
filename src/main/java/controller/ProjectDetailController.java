package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Projects;
import entity.Status;
import entity.Tasks;
import entity.Users;
import services.ProjectsServices;
import services.StatusServices;
import services.TasksServices;
import services.UsersServices;

@WebServlet(name="ProjectDetailController", urlPatterns = {"/projects/project-detail"})
public class ProjectDetailController extends HttpServlet{
	private UsersServices usersServices = new UsersServices();
	private StatusServices statusServices = new StatusServices();
	private TasksServices  tasksServices = new TasksServices();
	private ProjectsServices projectsServices = new ProjectsServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idProject = 0;
		try {
			idProject = Integer.parseInt(req.getParameter("id-detail"));
			
			
		} catch (Exception e) {
			System.out.println("ProjectDetailController : " + e.getMessage());
		}
		Projects projects = projectsServices.getProjectById(idProject);
		List<Users> listUsers = usersServices.getUserByIdJob(idProject);
		List<Status> listStatus = statusServices.getAllStatus();
		
//		Tìm tất cả công việc trên tất cả status của users
		for (Users users : listUsers) {
			List<Status> listStatusOfUser = new ArrayList<Status>();
			for (Status status : listStatus) {
				Status statusCoppy = new Status();
				
				List<Tasks> listTasksOfStatus = tasksServices.getTasksByIdStatusAndIdUserAndIdJob(status.getId(), users.getId(), idProject);
				statusCoppy.setTasksListStatus(listTasksOfStatus);
				
				listStatusOfUser.add(statusCoppy);
			}
			users.setAllStatus(listStatusOfUser);
		}
		
//		GET ALL WIDTH STATUS
		List<Tasks> listTasks = tasksServices.getTasksByIdJob(idProject);
		for (Status status : listStatus) {
			status.setTasksListStatus(tasksServices.getTasksByIdStatusAndIdJob(status.getId() ,idProject));
			double countStatusTask = status.getTasksListStatus().size();
			double totalStatusTask = listTasks.size();
			int statusWidth = (int) Math.round(countStatusTask / totalStatusTask * 100 ) ;
			status.setWidthStatus(statusWidth);
		}
		
		
		List<String> ListColorStatusText = new ArrayList<>(
			    List.of("text-megna", "text-primary","text-danger")
			);
		List<String> ListColorStatusProcess = new ArrayList<String>(			    
				List.of("progress-bar-megna","progress-bar-primary","progress-bar-danger")
		);

		req.setAttribute("projects", projects);
		req.setAttribute("listStatus", listStatus);
		req.setAttribute("ListColorStatusText", ListColorStatusText);
		req.setAttribute("ListColorStatusProcess", ListColorStatusProcess);
		
		req.setAttribute("listUsers", listUsers);
		req.getRequestDispatcher("/groupwork-details.jsp").forward(req, resp);
	}
}

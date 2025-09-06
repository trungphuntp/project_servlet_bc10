package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Projects;
import entity.Tasks;
import entity.Users;
import services.ProjectsServices;
import services.TasksServices;

@WebServlet(name="TaskTableController" , urlPatterns = "/tasks")
public class TaskTableController extends HttpServlet{
	private TasksServices tasksServices = new TasksServices();
	private ProjectsServices projectsServices  = new ProjectsServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Tasks> listTasks = new ArrayList<Tasks>();
		List<Projects> listProjects = null;

		
		HttpSession session = req.getSession(false);
		Users users = (Users) session.getAttribute("userLogin");
		if (session != null ) {
			if (users.getId() > 0) {
//				ROLE ADMIN
				if (users.getRole_id() == 1) {
					listTasks	= tasksServices.getAllTasks();
				}
//				ROLE LEADER 
				if (users.getRole_id() == 2) {
					listProjects =  projectsServices.getProjectByIdUser(users.getId());;
					if (listProjects != null ) {
						for (Projects projects : listProjects) {
							List<Tasks> listTasksByIdProject = tasksServices.getTasksByIdJob(projects.getId());
							if (listTasks != null) {
								listTasks.addAll(listTasksByIdProject);
							}
						}
					}
					
					
				}
			}
		}

		if (!listTasks.isEmpty()) {
			req.setAttribute("listTasks", listTasks);
		}
		req.getRequestDispatcher("/task.jsp").forward(req, resp);
	}
}

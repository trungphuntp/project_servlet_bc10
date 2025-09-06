package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Projects;
import entity.Status;
import entity.Tasks;
import entity.Users;
import services.ProjectsServices;
import services.StatusServices;
import services.TasksServices;
import services.UsersServices;

@WebServlet(name = "AddTaskController",urlPatterns = {"/tasks/task-add","/tasks/task-edit"})
public class AddTaskController extends HttpServlet{
	private TasksServices tasksServices = new TasksServices();
	private ProjectsServices projectsServices = new ProjectsServices();
	private UsersServices usersServices = new UsersServices();
	private StatusServices statusServices = new StatusServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Projects> listProjects = null;
		List<Users> listUsers = usersServices.getAllUsers();
		List<Status> listStatus = statusServices.getAllStatus();
		int isEdit = 0;
		
		HttpSession session = req.getSession(false);
		Users users = (Users) session.getAttribute("userLogin");
		if (session != null ) {
			if (users.getId() > 0) {
//				ROLE ADMIN
				if (users.getRole_id() == 1) {
					listProjects = projectsServices.getAllProjects();
				}
//				ROLE LEADER 
				if (users.getRole_id() == 2) {
					listProjects = projectsServices.getProjectByIdUser(users.getId());
				}
			}
		}
		
//		Đường dẫn /tasks/task-edit
		if (req.getServletPath().equals("/tasks/task-edit")) {
			isEdit = 1;
			
			int idEdit = 0;
			try {
			 idEdit = Integer.parseInt(req.getParameter("id-edit"));
			} catch (Exception e) {
				System.out.println("AddTaskController : " + e.getMessage());
			}
			Tasks taskEdit = tasksServices.getTaskById(idEdit);
			req.setAttribute("taskEdit", taskEdit);			
		}

		req.setAttribute("isEdit", isEdit);
		req.setAttribute("listProjects", listProjects);
		req.setAttribute("listUsers", listUsers);
		req.setAttribute("listStatus", listStatus);
		req.getRequestDispatcher("/task-add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int projectId = 0;
		String nameTask = "";
		int userId = 0;
		String startDate =  "";
		String endDate = "";
		try {
			 projectId = Integer.parseInt(req.getParameter("project").trim());
			 nameTask = req.getParameter("nameTask").trim();
			 userId = Integer.parseInt(req.getParameter("user").trim());
			 startDate = req.getParameter("startDate");
			 endDate = req.getParameter("endDate");
		} catch (Exception e) {
			System.out.println("AddTaskController : " + e.getMessage());
		}
		
//		đường dẫn /tasks/task-add
		if (req.getServletPath().equals("/tasks/task-add")) {
			if (!nameTask.isEmpty() && !startDate.isEmpty() && projectId != 0 && userId != 0) {
				tasksServices.addTask(nameTask, projectId, userId, startDate, endDate);
				resp.sendRedirect(req.getContextPath()+"/tasks");
				return;
			}
		}
		
//		đường dẫn /tasks/task-edit
		if (req.getServletPath().equals("/tasks/task-edit")) {
			if (!nameTask.isEmpty() && !startDate.isEmpty() && projectId != 0 && userId != 0) {
				int idEdit = 0;
				int statusId = 0;
				try {
					 idEdit = Integer.parseInt(req.getParameter("id-edit"));
					 statusId = Integer.parseInt(req.getParameter("statusId"));
				} catch (Exception e) {
					System.out.println("AddTaskController : " + e.getMessage());
				}
				if (idEdit != 0 && statusId != 0) {
					tasksServices.editTask(nameTask, startDate, endDate, userId, projectId, statusId, idEdit);
					resp.sendRedirect(req.getContextPath()+"/tasks");
					return;
				}
			}
		}
		req.getRequestDispatcher("/task-add.jsp").forward(req, resp);
	}
}

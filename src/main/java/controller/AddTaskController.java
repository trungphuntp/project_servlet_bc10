package controller;

import java.io.IOException;
import java.sql.Date;
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

@WebServlet(name = "AddTaskController",urlPatterns = {"/task-add","/task-edit"})
public class AddTaskController extends HttpServlet{
	private TasksServices tasksServices = new TasksServices();
	private ProjectsServices projectsServices = new ProjectsServices();
	private UsersServices usersServices = new UsersServices();
	private StatusServices statusServices = new StatusServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Projects> listProjects = projectsServices.getAllProjects();
		List<Users> listUsers = usersServices.getAllUsers();
		List<Status> listStatus = statusServices.getAllStatus();
		int isEdit = 0;
		
		if (req.getServletPath().equals("/task-edit")) {
			isEdit = 1;
			int idEdit = Integer.parseInt(req.getParameter("id-edit"));
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
		int projectId = Integer.parseInt(req.getParameter("project").trim());
		String nameTask = req.getParameter("nameTask").trim();
		int userId = Integer.parseInt(req.getParameter("user").trim());
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		
		if (req.getServletPath().equals("/task-add")) {
			if (!nameTask.isEmpty() && !startDate.isEmpty() && projectId != 0 && userId != 0) {
				Date startDateSql = Date.valueOf(startDate);
				Date endDateSql = null;
				if (!endDate.isEmpty()) {
					endDateSql = Date.valueOf(endDate);
				}
				tasksServices.addTask(nameTask, projectId, userId, startDateSql, endDateSql);
				resp.sendRedirect(req.getContextPath()+"/tasks");
				return;
			}
		}
		if (req.getServletPath().equals("/task-edit")) {
			if (!nameTask.isEmpty() && !startDate.isEmpty() && projectId != 0 && userId != 0) {
				Date startDateSql = Date.valueOf(startDate);
				Date endDateSql = null;
				if (!endDate.isEmpty()) {
					endDateSql = Date.valueOf(endDate);
				}
				int idEdit = Integer.parseInt(req.getParameter("id-edit"));
				int statusId = Integer.parseInt(req.getParameter("statusId"));
				if (idEdit != 0 && statusId != 0) {
					tasksServices.editTask(nameTask, startDateSql, endDateSql, userId, projectId, statusId, idEdit);
					resp.sendRedirect(req.getContextPath()+"/tasks");
					return;
				}
			}
		}
		
		

		
		req.getRequestDispatcher("/task-add.jsp").forward(req, resp);
	}
}

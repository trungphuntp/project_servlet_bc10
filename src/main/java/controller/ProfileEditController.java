package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Projects;
import entity.Status;
import entity.Tasks;
import services.ProjectsServices;
import services.StatusServices;
import services.TasksServices;

@WebServlet(name = "ProfileEditController" , urlPatterns = "/profile/profile-edit")
public class ProfileEditController extends HttpServlet {
	private ProjectsServices projectsServices = new ProjectsServices();
	private StatusServices statusServices = new StatusServices();
	private TasksServices tasksServices  =new TasksServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Projects> listProjects = projectsServices.getAllProjects();
		List<Status> listStatus = statusServices.getAllStatus();
		List<Tasks> listTasks = tasksServices.getAllTasks();
		
		Cookie[] listCookie = req.getCookies();
		int idUser = 0;
		if (listCookie != null) {
			for (Cookie cookie : listCookie) {
				if (cookie.getName().equals("idUser")) {
					idUser = Integer.parseInt(cookie.getValue());
				}
			}
		}
		Tasks tasks = new Tasks();
		
		int idTask = 0;
		try {
			idTask = Integer.parseInt(req.getParameter("id-task"));
			tasks = tasksServices.getTaskByIdAndIdUser(idTask, idUser);
		} catch (Exception e) {
			System.out.println("ProfileEditController : " + e.getMessage());
		}

		req.setAttribute("tasks", tasks);
		req.setAttribute("listProjects", listProjects);
		req.setAttribute("listStatus", listStatus);
		req.getRequestDispatcher("/profile-edit.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int projectId = 0;
		String nameTask = "";
		String startDate =  "";
		String endDate = "";
		int idTask = 0;
		int statusId = 0;

		try {
			 projectId = Integer.parseInt(req.getParameter("projectId"));
			 nameTask = req.getParameter("nameTask").trim();
			 startDate = req.getParameter("startDate");
			 endDate = req.getParameter("endDate");
			 idTask = Integer.parseInt(req.getParameter("id-task"));
			 statusId = Integer.parseInt(req.getParameter("statusId"));

		} catch (Exception e) {
			System.out.println("ProfileEditController post: " + e.getMessage());
		}
		Cookie[] listCookie = req.getCookies();
		int idUser = 0;
		if (listCookie != null) {
			for (Cookie cookie : listCookie) {
				if (cookie.getName().equals("idUser")) {
					idUser = Integer.parseInt(cookie.getValue());
				}
			}
		}
		
		if (!startDate.isEmpty() && statusId != 0 && idTask != 0 && idUser != 0 && projectId != 0 && !nameTask.isEmpty()) {
			tasksServices.editTask(nameTask, startDate, endDate, idUser, projectId, statusId, idTask);
		}
		resp.sendRedirect(req.getContextPath()+"/profile");
	}
}

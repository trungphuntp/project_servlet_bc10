package controller;

import java.io.IOException;
import java.sql.Date;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Projects;
import entity.Users;
import services.ProjectsServices;
import services.TasksServices;

@WebServlet(name="AddProjectController", urlPatterns = {"/projects/project-add", "/projects/project-edit"})
public class AddProjectController extends HttpServlet{
	private ProjectsServices projectsServices = new ProjectsServices();
	private TasksServices tasksServices = new TasksServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int isEdit = 0;
//		đường dẫn /projects/project-edit
		if (req.getServletPath().equals("/projects/project-edit")) {
			isEdit = 1;
			int idEdit = 0;
			try {
				idEdit = Integer.parseInt(req.getParameter("id-edit"));
			} catch (Exception e) {
				System.out.println("AddProjectController : " + e.getMessage());
			}
			
			Projects projects = projectsServices.getProjectById(idEdit);
			
			if (projects.getId() > 0) {
				req.setAttribute("projects", projects);
				
			}

		}
		
		req.setAttribute("isEdit", isEdit);
		req.getRequestDispatcher("/groupwork-add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nameProject = "";
		String startDate = "";
		String endDate = "";
		HttpSession session = req.getSession(false);
		Users users = (Users) session.getAttribute("userLogin");
		

		
		try {
			 nameProject = req.getParameter("nameProject").trim();
			 startDate = req.getParameter("startDate").trim();
			 endDate = req.getParameter("endDate").trim();
		} catch (Exception e) {
			System.out.println("AddProjectController : " + e.getMessage());
		}
		
//		đường dẫn /projects/project-add
		if (req.getServletPath().equals("/projects/project-add")) {
			if (!nameProject.isEmpty() && !startDate.isEmpty()) {
			projectsServices.addProject(nameProject, startDate, endDate);	
			if (session != null ) {
				if (users.getId() > 0) {
//					Thêm job vai trò quản lí sau khi thêm dự án mới
					String nameTask = "Quản lí dự án";
					Projects projectsAdd = projectsServices.getProjectByNameJob(nameProject);
//					ROLE LEADER 
					if (users.getRole_id() == 2) {
						tasksServices.addTask(nameTask,projectsAdd.getId(),users.getId(),startDate, endDate);
					}
				}
			}
			
			}
		}
		
//		đường dẫn /projects/project-edit
		if (req.getServletPath().equals("/projects/project-edit")) {
			int idEdit = Integer.parseInt(req.getParameter("id-edit"));
			if (!nameProject.isEmpty() && !startDate.isEmpty()) {
				if (idEdit > 0) {
					projectsServices.editProjectById(nameProject, startDate, endDate, idEdit);
				}
			}
		}

		
		resp.sendRedirect(req.getContextPath() + "/projects");
	}
}

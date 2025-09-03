package controller;

import java.io.IOException;
import java.sql.Date;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Projects;
import services.ProjectsServices;

@WebServlet(name="AddProjectController", urlPatterns = {"/project-add", "/project-edit"})
public class AddProjectController extends HttpServlet{
	private ProjectsServices projectsServices = new ProjectsServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int isEdit = 0;
		if (req.getServletPath().equals("/project-edit")) {
			isEdit = 1;
			int idEdit = Integer.parseInt(req.getParameter("id-edit"));
			Projects projects = projectsServices.findProjectById(idEdit);
		
			
			if (projects.getId() > 0) {
				req.setAttribute("projects", projects);
			}
		}
		
		req.setAttribute("isEdit", isEdit);
		req.getRequestDispatcher("/groupwork-add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nameProject = req.getParameter("nameProject").trim();
		String startDate = req.getParameter("startDate").trim();
		String endDate = req.getParameter("endDate").trim();
		
		if (req.getServletPath().equals("/project-add")) {
			if (!nameProject.isEmpty() && !startDate.isEmpty()) {
				Date startDateSql = null;
				Date endDateSql = null;
				startDateSql =  Date.valueOf(startDate);
				if (!endDate.isEmpty()) {
					endDateSql =  Date.valueOf(endDate);
				}
			projectsServices.addProject(nameProject, startDateSql, endDateSql);	
			}
		}
		
		if (req.getServletPath().equals("/project-edit")) {
			int idEdit = Integer.parseInt(req.getParameter("id-edit"));
			
			if (!nameProject.isEmpty() && !startDate.isEmpty()) {
				if (idEdit > 0) {
					Date startDateSql = null;
					Date endDateSql = null;
					startDateSql =  Date.valueOf(startDate);
					if (!endDate.isEmpty()) {
						endDateSql =  Date.valueOf(endDate);
					}
					projectsServices.editProjectById(nameProject, startDateSql, endDateSql, idEdit);
				}
			}
		}

		
		resp.sendRedirect(req.getContextPath() + "/projects");
	}
}

package controller;

import java.io.IOException;
import java.sql.Date;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.ProjectsServices;

@WebServlet(name="AddProjectController", urlPatterns = {"/project-add"})
public class AddProjectController extends HttpServlet{
	ProjectsServices projectsServices = new ProjectsServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/groupwork-add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nameProject = req.getParameter("nameProject").trim();
		String startDate = req.getParameter("startDate").trim();
		String endDate = req.getParameter("endDate").trim();
		if (!nameProject.isEmpty() && !startDate.isEmpty()) {
			Date startDateSql = null;
			Date endDateSql = null;
			startDateSql =  Date.valueOf(startDate);
			if (!endDate.isEmpty()) {
				endDateSql =  Date.valueOf(endDate);
			}
			
		projectsServices.addProject(nameProject, startDateSql, endDateSql);	
		}
		resp.sendRedirect(req.getContextPath() + "/projects");
	}
}

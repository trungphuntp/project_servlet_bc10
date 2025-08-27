package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Projects;
import services.ProjectsServices;

@WebServlet(name="ProjectTableController" , urlPatterns = "/projects")
public class ProjectTableController extends HttpServlet{
	private ProjectsServices projectsServices  = new ProjectsServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Projects> listProjects = projectsServices.getAllProjects();
		
		if (!listProjects.isEmpty()) {
			req.setAttribute("listProjects", listProjects);
		}
		req.getRequestDispatcher("/groupwork.jsp").forward(req, resp);
	}
}

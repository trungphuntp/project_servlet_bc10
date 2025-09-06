package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Projects;
import entity.Users;
import services.ProjectsServices;

@WebServlet(name="ProjectTableController" , urlPatterns = "/projects")
public class ProjectTableController extends HttpServlet{
	private ProjectsServices projectsServices  = new ProjectsServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Projects> listProjects = projectsServices.getAllProjects();
		HttpSession session = req.getSession(false);
		Users users = (Users) session.getAttribute("userLogin");
		if (session != null ) {
			if (users.getId() > 0) {
//				ROLE ADMIN
				if (users.getRole_id() == 1) {
					listProjects	= projectsServices.getAllProjects();
				}
//				ROLE LEADER 
				if (users.getRole_id() == 2) {
					listProjects = projectsServices.getProjectByIdUser(users.getId());
				}
			}
		}
		
		
		if (!listProjects.isEmpty()) {
			req.setAttribute("listProjects", listProjects);
		}
		req.getRequestDispatcher("/groupwork.jsp").forward(req, resp);
	}
}

package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.ProjectsServices;

@WebServlet(name = "DeleteProjectController", urlPatterns = "/project-delete")
public class DeleteProjectController extends HttpServlet{
	private ProjectsServices projectsServices = new ProjectsServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idDelete = Integer.parseInt(req.getParameter("id-delete"));
		if (idDelete > 0) {
			projectsServices.removeProject(idDelete);
			resp.sendRedirect(req.getContextPath()+"/projects");
			return;
		}
		req.getRequestDispatcher("/groupwork.jsp").forward(req, resp);
	}
}

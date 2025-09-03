package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Projects;
import entity.Status;
import services.ProjectsServices;
import services.StatusServices;

@WebServlet(name = "ProfileEditController" , urlPatterns = "/profile-edit")
public class ProfileEditController extends HttpServlet {
	private ProjectsServices projectsServices = new ProjectsServices();
	private StatusServices statusServices = new StatusServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Projects> listProjects = projectsServices.getAllProjects();
		List<Status> listStatus = statusServices.getAllStatus();
		Cookie[] listCookie = req.getCookies();
		int idUser = 0;
		if (listCookie != null) {
			for (Cookie cookie : listCookie) {
				if (cookie.getName().equals("idUser")) {
					idUser = Integer.parseInt(cookie.getValue());
				}
			}
		}
		
		int idTask = 0;
		try {
			idTask = Integer.parseInt(req.getParameter("id-task"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ProfileEditController : " + e.getMessage());
		}
		
		
		
		req.getRequestDispatcher("/profile-edit.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}

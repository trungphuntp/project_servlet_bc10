package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Tasks;
import services.TasksServices;


@WebServlet(name = "ProfileController", urlPatterns = "/profile")
public class ProfileController extends HttpServlet{
	private TasksServices tasksServices = new TasksServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] listCookie = req.getCookies();
		int idUser = 0;
		if (listCookie != null) {
			for (Cookie cookie : listCookie) {
				if (cookie.getName().equals("idUser")) {
					idUser = Integer.parseInt(cookie.getValue());
				}
			}
		}
		List<Tasks> listTasks = tasksServices.getTaskByIdUser(idUser);
		
		req.setAttribute("listTasks", listTasks);
		req.getRequestDispatcher("/profile.jsp").forward(req, resp);
	}
}

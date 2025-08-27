package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Users;
import services.UsersServices;

@WebServlet(name="UsersTableController", urlPatterns = {"/user-table"})

public class UsersTableController extends HttpServlet {
	private UsersServices usersServices = new UsersServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Users> listUsers = usersServices.getAllUsers();
		if (!listUsers.isEmpty()) {
			req.setAttribute("listUsers", listUsers);
		}
		
		req.getRequestDispatcher("/user-table.jsp").forward(req, resp);
	}
}

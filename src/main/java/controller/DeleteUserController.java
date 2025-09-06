package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.UsersServices;

@WebServlet(name = "DeleteUserController",urlPatterns = "/users/user-delete")
public class DeleteUserController extends HttpServlet{
	private UsersServices usersServices = new UsersServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int deleteId = 0;
		try {
		 deleteId = Integer.parseInt(req.getParameter("id-delete"));

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("DeleteUserController : " + e.getMessage());
		}
		if (deleteId > 0) {
			usersServices.removeUser(deleteId);
			resp.sendRedirect(req.getContextPath()+"/users");
			return;
		}
		req.getRequestDispatcher("/user-table.jsp").forward(req, resp);
	}
}

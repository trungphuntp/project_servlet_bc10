package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.RolesServices;


@WebServlet(name="DeleteRoleController", urlPatterns = "/role-delete")
public class DeleteRoleController extends HttpServlet{
	private RolesServices rolesServices = new RolesServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idDelete = Integer.parseInt(req.getParameter("delete-id").trim());
		if (idDelete != 0) {
			rolesServices.removeRoleById(idDelete);
			resp.sendRedirect(req.getContextPath() + "/roles");
			return;
		}
		req.getRequestDispatcher("/role-table.jsp").forward(req, resp);
	}
}

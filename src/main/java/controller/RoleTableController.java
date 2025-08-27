package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Roles;
import services.RolesServices;

@WebServlet(name="RoleTableController", urlPatterns = {"/roles"})
public class RoleTableController extends HttpServlet{
	private RolesServices rolesServices = new RolesServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Roles> listRoles = rolesServices.getAllRoles();
		
		if (!listRoles.isEmpty()) {
			req.setAttribute("listRoles", listRoles);
		}
		req.getRequestDispatcher("/role-table.jsp").forward(req, resp);
	}
}

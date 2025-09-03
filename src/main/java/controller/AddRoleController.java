package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Roles;
import services.RolesServices;

@WebServlet(name="AddRoleController",urlPatterns = {"/role-add","/role-edit"})
public class AddRoleController extends HttpServlet {
	private RolesServices rolesServices = new RolesServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		int isEdit = 0;
		if (req.getServletPath().equals("/role-edit")) {
			int idEdit = Integer.parseInt(req.getParameter("id-edit").trim());
			Roles rolesEdit = rolesServices.findRoleById(idEdit);
			String name = rolesEdit.getName();
			String desc = rolesEdit.getDesc();
			isEdit = 1;
	
			req.setAttribute("nameEdit", name);
			req.setAttribute("descEdit", desc);
		}
		
		req.setAttribute("isEdit", isEdit);
		req.getRequestDispatcher("/role-add.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nameRole = req.getParameter("nameRole").trim();
		String desc = req.getParameter("desc").trim();
		
		if (req.getServletPath().equals("/role-add") ) {
			rolesServices.addRole(nameRole, desc);
			
		}
		
		if (req.getServletPath().equals("/role-edit")) {
			int idEdit = Integer.parseInt(req.getParameter("id-edit").trim());
			rolesServices.editRole(nameRole, desc, idEdit);
		}
		
	
		resp.sendRedirect(req.getContextPath() + "/roles");
	}
}

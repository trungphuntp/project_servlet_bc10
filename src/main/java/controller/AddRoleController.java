package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Roles;
import services.RolesServices;

@WebServlet(name="AddRoleController",urlPatterns = {"/roles/role-add","/roles/role-edit"})
public class AddRoleController extends HttpServlet {
	private RolesServices rolesServices = new RolesServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		int isEdit = 0;
//		đường dẫn /roles/role-add
		if (req.getServletPath().equals("/roles/role-add")) {
			int idEdit = 0;
			try {
				 idEdit = Integer.parseInt(req.getParameter("id-edit"));
			} catch (Exception e) {
				System.out.println("AddRoleController : " + e.getMessage());
			}
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
		String nameRole = "";
		String desc = "";
		
		try {
			 nameRole = req.getParameter("nameRole").trim();
			 desc = req.getParameter("desc").trim();
		} catch (Exception e) {
			System.out.println("AddRoleController : " + e.getMessage());
		}
		
//		đường dẫn /roles/role-add
		if (req.getServletPath().equals("/roles/role-add") ) {
			rolesServices.addRole(nameRole, desc);
			
		}
		
//		đường dẫn /roles/role-edit
		if (req.getServletPath().equals("/roles/role-edit")) {
			int idEdit = 0;
			try {
				 idEdit = Integer.parseInt(req.getParameter("id-edit").trim());
			} catch (Exception e) {
				System.out.println("AddRoleController : " + e.getMessage());
			}
			rolesServices.editRole(nameRole, desc, idEdit);
		}
		
	
		resp.sendRedirect(req.getContextPath() + "/roles");
	}
}

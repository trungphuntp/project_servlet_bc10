package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Roles;
import entity.Users;
import services.RolesServices;
import services.UsersServices;

@WebServlet(name="AddUserController", urlPatterns = {"/user-add","/user-edit"})
public class AddUserController extends HttpServlet{
	private UsersServices usersServices = new UsersServices();
	private RolesServices rolesServices = new RolesServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int usersPage = 0;
		

		if (req.getServletPath().equals("/user-edit")) {
			usersPage=1;
			int idEdit = Integer.parseInt(req.getParameter("id-edit"));
			Users users = usersServices.findUsersById(idEdit);
			req.setAttribute("users", users);
		}

		
		List<Roles> listRoles = rolesServices.getAllRoles();
		req.setAttribute("listRoles", listRoles);
		req.setAttribute("usersPage", usersPage);
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		int role = Integer.parseInt(req.getParameter("roles"));
		
		if (req.getServletPath().equals("/user-add")) {
			if (!fullname.isEmpty() && !email.isEmpty() && !password.isEmpty() ) {
				usersServices.addUser(fullname, email, password, phone, role);
			}
		}
		if (req.getServletPath().equals("/user-edit")) {
			int idEdit = Integer.parseInt(req.getParameter("id-edit"));
			usersServices.editUser(fullname, email, password, phone, role, idEdit);
		}
		
		resp.sendRedirect(req.getContextPath()+"/users");
	}
}

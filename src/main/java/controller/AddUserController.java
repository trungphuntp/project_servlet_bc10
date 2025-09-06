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

@WebServlet(name="AddUserController", urlPatterns = {"/users/user-add","/users/user-edit"})
public class AddUserController extends HttpServlet{
	private UsersServices usersServices = new UsersServices();
	private RolesServices rolesServices = new RolesServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int isEdit = 0;
		
//		Đường dẫn /users/user-edit
		if (req.getServletPath().equals("/users/user-edit")) {
			isEdit=1;
			int idEdit = 0;
			try {
			 idEdit = Integer.parseInt(req.getParameter("id-edit"));
			} catch (Exception e) {
				System.out.println("AddUserController : " + e.getMessage());
			}
			Users users = usersServices.getUsersById(idEdit);
			req.setAttribute("users", users);
		}

		
		List<Roles> listRoles = rolesServices.getAllRoles();
		req.setAttribute("listRoles", listRoles);
		req.setAttribute("isEdit", isEdit);
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fullname = "";
		String email = "";
		String password = "";
		String phone = "";
		int role = 0;
		
		try {
			 fullname = req.getParameter("fullname");
			 email = req.getParameter("email");
			 password = req.getParameter("password");
			 phone = req.getParameter("phone");
			 role = Integer.parseInt(req.getParameter("roles"));
		} catch (Exception e) {
			System.out.println("AddUserController : " + e.getMessage());
		}
		
//		Đường dẫn /users/user-add
		if (req.getServletPath().equals("/users/user-add")) {
			if (!fullname.isEmpty() && !email.isEmpty() && !password.isEmpty() ) {
				usersServices.addUser(fullname, email, password, phone, role);
			}
		}
		
//		Đường dẫn /users/user-edit
		if (req.getServletPath().equals("/users/user-edit")) {
			int idEdit = 0;
			try {
				idEdit = Integer.parseInt(req.getParameter("id-edit"));
			} catch (Exception e) {
				System.out.println("AddUserController : " + e.getMessage());
			}
			usersServices.editUser(fullname, email, password, phone, role, idEdit);
		}
		
		resp.sendRedirect(req.getContextPath()+"/users");
	}
}

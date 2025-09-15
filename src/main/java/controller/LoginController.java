package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Users;
import services.AuthenticationServices;

@WebServlet(name="LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
	private AuthenticationServices authenticationRepository = new AuthenticationServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] listCookie = req.getCookies();
		String email = "";
		String password = "";
		String isRemember = "";

		
		for (Cookie cookie : listCookie) {
			if (cookie.getName().equals("email")) {
				email = cookie.getValue();
			}
			if (cookie.getName().equals("password")) {
				password = cookie.getValue();
			}
			if (cookie.getName().equals("isRemember")) {
				isRemember = cookie.getValue();
			}
		}
		if (!isRemember.isEmpty()) {
			req.setAttribute("email", email);
			req.setAttribute("password", password);
		}
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = "";
		String password = "";
		String remember = "";
		
		try {
			 email = req.getParameter("email").toLowerCase().trim();
			 password = req.getParameter("password");
			 remember = req.getParameter("remember");
			
		} catch (Exception e) {
			System.out.println("LoginController : " + e.getMessage());
		}
		
		List<Users> listUsers = authenticationRepository.checkUserAccount(email, password, remember, req , resp);
		if (!listUsers.isEmpty()) {
			resp.sendRedirect(req.getContextPath() + "/");
			return;
		}


		resp.sendRedirect(req.getContextPath() + "/login");
	}
}

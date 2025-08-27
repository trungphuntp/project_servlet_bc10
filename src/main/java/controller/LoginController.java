package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.AuthenticationServices;

@WebServlet(name="LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
	private AuthenticationServices authenticationRepository = new AuthenticationServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] listCookie = req.getCookies();
		String email = "";
		String password = "";
		for (Cookie cookie : listCookie) {
			if (cookie.getName().equals("email")) {
				email = cookie.getValue();
			}
			if (cookie.getName().equals("password")) {
				password = cookie.getValue();
			}
		}
		if (!email.isEmpty() && !password.isEmpty()) {
			req.setAttribute("email", email);
			req.setAttribute("password", password);
		}
		
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email").toLowerCase().trim();
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		authenticationRepository.checkUserAccount(email, password, remember, req , resp);
		resp.sendRedirect(req.getContextPath() + "/login");
		
	}
}

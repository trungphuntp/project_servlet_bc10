package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="LogoutUsersController", urlPatterns = {"/logout"})
public class LogoutUsersController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] listCookie = req.getCookies();
		if (listCookie != null) {
			for (Cookie cookie : listCookie) {
		        cookie.setValue("");     
		        cookie.setMaxAge(0);      
		        resp.addCookie(cookie);
			}
		}
		
		HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
		
		resp.sendRedirect(req.getContextPath() + "/");
	}
}

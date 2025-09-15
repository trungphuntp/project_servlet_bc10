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
		boolean isRememberActive = false;
		if (listCookie != null) {
			for (Cookie cookie : listCookie) {
				if (cookie.getName().equals("isRemember")) {
					isRememberActive = true;
				}
			}
		}

        
		if (listCookie != null) {
			for (Cookie cookie : listCookie) {
				if (isRememberActive) {
					if (!cookie.getName().equals("email") &&
						!cookie.getName().equals("password")&& 
						!cookie.getName().equals("isRemember")) {
						cookie.setValue("");      
				        cookie.setMaxAge(0);      
				        resp.addCookie(cookie);
					}
				}else {
					cookie.setValue("");      
			        cookie.setMaxAge(0);      
			        resp.addCookie(cookie);
				}
			}
		}
		
		HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
		
		resp.sendRedirect(req.getContextPath() + "/");
	}
}

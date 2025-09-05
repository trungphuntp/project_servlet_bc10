package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Users;
import services.UsersServices;

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/login"})
public class LoginFilter implements Filter{
	private UsersServices usersServices = new UsersServices();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		Cookie[] listCookie = req.getCookies();
//		FIND USER LOGIN
		String email = "";
		String password = "";
		int idUser = 0;
		if (listCookie != null ) {
			for (Cookie cookie : listCookie) {
				if (cookie.getName().equals("email")) {
					email = cookie.getValue();
				}
				if (cookie.getName().equals("password")) {
					password = cookie.getValue();
				}
				if (cookie.getName().equals("idUser")) {
					idUser = Integer.parseInt(cookie.getValue());
				}
			}
		}
		boolean isLogin = false;
		Users user = new Users();
		if (!email.isEmpty() && !password.isEmpty() && idUser > 0) {
			isLogin = true;
			req.setAttribute("isLogin", isLogin);
			user = usersServices.getUsersByIdEmailPassword(idUser, email, password);
		}
		if (user.getId() <= 0 || user.getEmail().isEmpty() || user.getPassword().isEmpty() ) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + "/");
		}
		
		
	}

}

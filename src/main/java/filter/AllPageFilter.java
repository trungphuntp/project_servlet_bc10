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

import entity.Users;
import services.UsersServices;

@WebFilter(filterName = "AllPageFilter", urlPatterns = { "/*" })
public class AllPageFilter implements Filter {
	private UsersServices usersServices = new UsersServices();
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpServletRequest req = (HttpServletRequest) request;
		Cookie[] listCookie = req.getCookies();

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
		if (!email.isEmpty() && !password.isEmpty() && idUser != 0) {
			isLogin = true;
			req.setAttribute("isLogin", isLogin);
			user = usersServices.getUsersAbsolute(idUser, email, password);
		}


		req.setAttribute("userCurrent", user);
		
		chain.doFilter(request, response);
	}

}

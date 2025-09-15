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
import javax.servlet.http.HttpSession;

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
		
//		Truyền thông tin USERS cho tất cả page
		boolean isLogin = false;
		Users user = new Users();
		if (!email.isEmpty() && !password.isEmpty() && idUser > 0) {
			isLogin = true;
			req.setAttribute("isLogin", isLogin);
			user = usersServices.getUsersByIdEmailPassword(idUser, email, password);
		}
		req.setAttribute("userCurrent", user);
		
//		Gắn user vô season để sử dụng ở controller
		HttpSession session = req.getSession();
		session.setAttribute("userLogin", user);
		

//		AUTHENTICATION 
		// Check trang blank để không vòng lặp
		if (req.getServletPath().equals("/blank") ||
				req.getServletPath().equals("") ||
				req.getServletPath().equals("/404") ||
				req.getServletPath().equals("/login")) {
		chain.doFilter(request, response);
		return;
		}
		
//		Bỏ qua file tĩnh
		if (req.getServletPath().startsWith("/css") 
		        || req.getServletPath().startsWith("/js") 
		        || req.getServletPath().startsWith("/plugins") 
		        || req.getServletPath().startsWith("/bootstrap") 
		        || req.getServletPath().startsWith("/less") 
		        || req.getServletPath().startsWith("/fonts")) {
		    chain.doFilter(request, response);
		    return;
		}
		
		/**
		 * Id Role
		 * 1 : Admin -> Quản trị
		 * 2 : Manager -> Manager
		 * 3 : Employee -> Nhân viên
		 */
		String path = req.getServletPath();

//		CHUYỂN TRANG 
		if (user.getId() > 0 && !user.getEmail().isEmpty() && !user.getPassword().isEmpty()) {
			// NHÂN VIÊN 
			if (user.getRole_id() == 3) {
				if (path.startsWith("/users") || 
						path.startsWith("/roles") ||
						path.startsWith("/projects") ||
						path.startsWith("/tasks"))
				{
					resp.sendRedirect(req.getContextPath() + "/404");
					return;
				}
			}
			// MANAGER
			if (user.getRole_id() == 2) {
				if (path.startsWith("/roles")) 
				{
					resp.sendRedirect(req.getContextPath() + "/404");
					return;
				}

			}
			// ADMIN
			if (user.getRole_id() == 1) {
				
			}
			chain.doFilter(request, response);
			return;
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

}

package services;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Users;
import repository.AuthenticationRepository;

public class AuthenticationServices {
	private AuthenticationRepository authenticationRepository = new AuthenticationRepository();
	
	public void checkUserAccount(String email, String password, String remember, HttpServletRequest req, HttpServletResponse resp) {
		List<Users> listUsers = authenticationRepository.findUser(email, password);
		
		if (listUsers.isEmpty()) {
			System.out.println("Thất bại");
		} else {
			System.out.println("Thành công");
			Cookie cIsLogin = new Cookie("isLogin", email);
			cIsLogin.setMaxAge(60 * 60);
			resp.addCookie(cIsLogin);
			
			if (remember != null) {
				Cookie cEmail = new Cookie("email", email);
				Cookie cPassword = new Cookie("password", password);
				cEmail.setMaxAge(60 * 60);
				cPassword.setMaxAge(60 * 60);
				resp.addCookie(cEmail);
				resp.addCookie(cPassword);
			}
			
		}
	}
}

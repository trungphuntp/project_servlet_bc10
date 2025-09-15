package services;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Users;
import repository.AuthenticationRepository;

public class AuthenticationServices {
	private AuthenticationRepository authenticationRepository = new AuthenticationRepository();
	
	public List<Users> checkUserAccount(String email, String password, String remember, HttpServletRequest req, HttpServletResponse resp) {
		List<Users> listUsers = authenticationRepository.findUser(email, password);
		if (listUsers.isEmpty()) {
			System.out.println("Thất bại");
		} else {
			System.out.println("Thành công");
			Cookie cEmail = new Cookie("email", email);
			Cookie cPassword = new Cookie("password", password);
			Cookie cIdUser = new Cookie("idUser", listUsers.get(0).getId()+"");
			
			cEmail.setMaxAge(60 * 60);
			cIdUser.setMaxAge(60 * 60);
			cPassword.setMaxAge(60 * 60);
			
			resp.addCookie(cEmail);
			resp.addCookie(cPassword);
			resp.addCookie(cIdUser);

			if (remember != null) {
				Cookie isRemenber = new Cookie("isRemember", "yes");
				isRemenber.setMaxAge(60 * 60);
				resp.addCookie(isRemenber);
			} else {
				Cookie isRemenber = new Cookie("isRemember", "");    
				isRemenber.setMaxAge(0);      
		        resp.addCookie(isRemenber);
			}
			
		}
		return listUsers;
	}
}

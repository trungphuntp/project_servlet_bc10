package services;

import java.util.List;

import entity.Users;
import repository.UsersRepository;

public class UsersServices {
	private UsersRepository repository = new UsersRepository();
	public List<Users> getAllUsers() {
		return repository.findAllUsers();
	}

}

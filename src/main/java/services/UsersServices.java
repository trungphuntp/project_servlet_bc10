package services;

import java.util.List;

import entity.Users;
import repository.UsersRepository;

public class UsersServices {
	private UsersRepository usersRepository = new UsersRepository();
	public List<Users> getAllUsers() {
		return usersRepository.findAllUsers();
	}
	
	public int addUser(String fullname, String email, String password, String phone, int rolesId) {
		return usersRepository.insertUser(fullname, email, password, phone, rolesId);
	}
	
	public int removeUser(int id) {
		return usersRepository.deleteUser(id);
	}
	
	public Users getUsersById(int id) {
		return usersRepository.findUsersById(id);
	}
	
	public Users getUsersAbsolute(int id, String email, String password) {
		return usersRepository.findUsersAbsolute(id, email, password);
	}
	
	public int editUser(String fullname, String email, String password, String phone, int roleId, int idEdit) {
		return usersRepository.updateUser(fullname, email, password, phone, roleId, idEdit);
	}
	
	

}

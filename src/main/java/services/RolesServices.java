package services;

import java.util.List;

import entity.Roles;
import repository.RolesRepository;

public class RolesServices {
	private RolesRepository rolesRepository = new RolesRepository();
	public List<Roles> getAllRoles() {
		return rolesRepository.findAllRoles();
	}
	
	public List<Roles> getAllRolesExceptAdmin() {
		return rolesRepository.findAllRolesExceptAdmin();
	}
	
	public int addRole(String name, String desc) {
		return rolesRepository.insertRole(name, desc);
	}
	
	public int removeRoleById(int id) {
		return rolesRepository.deleteRole(id);
	}
	
	public int editRole(String name, String desc, int id) {
		return rolesRepository.updateRole(name, desc, id);
	}
	
	public Roles findRoleById(int id) {
		return rolesRepository.findRoleById(id);
	}
}

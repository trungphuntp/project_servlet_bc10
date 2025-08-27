package services;

import java.util.List;

import entity.Roles;
import repository.RolesRepository;

public class RolesServices {
	private RolesRepository rolesRepository = new RolesRepository();
	public List<Roles> getAllRoles() {
		return rolesRepository.findAllRoles();
	}
}

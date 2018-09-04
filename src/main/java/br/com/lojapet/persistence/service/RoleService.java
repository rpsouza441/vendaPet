package br.com.lojapet.persistence.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojapet.model.Role;
import br.com.lojapet.persistence.repository.RoleRepository;


@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleRepository dao;

	@Transactional
	public void saveRole(Role role) {
		try {
			dao.save(role);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	public List<Role> getAllRoles() {
		try {
			return dao.findAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Role getRoleById(UUID id) {
		try {

			Optional<Role> roleOptional = dao.findById(id);
			Role role;
			if (roleOptional.isPresent()) {
				 role = roleOptional.get();
			} else {
				role = new Role();
			}
			return role;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Role getRoleByAuthority(String authority) {
		try {

			Optional<Role> roleOptional = dao.findByAuthority(authority);
			Role role;
			if (roleOptional.isPresent()) {
				 role = roleOptional.get();
			} else {
				role = new Role();
			}
			return role;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void deleteRole(UUID id) {
		try {
			dao.deleteById(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void updateRole(Role role) {
		try {
			dao.save(role);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

}

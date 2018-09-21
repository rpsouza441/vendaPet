package br.com.lojapet.persistence.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojapet.model.User;
import br.com.lojapet.persistence.repository.UserRepository;


@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository dao;

	@Transactional
	public void saveUser(User user) {
		try {
			dao.save(user);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	public List<User> getAllUsers() {
		try {
			return dao.findAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUserById(UUID id) {
		try {

			Optional<User> userOptional = dao.findById(id);
			User user;
			if (userOptional.isPresent()) {
				 user = userOptional.get();
			} else {
				user = new User();
			}
			return user;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUserByUsername(String username) {
		try {

			Optional<User> userOptional = dao.findByUsername(username);
			User user;
			if (userOptional.isPresent()) {
				 user = userOptional.get();
			} else {
				user = new User();
			}
			return user;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void deleteUser(UUID id) {
		try {
			dao.deleteById(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void updateUser(User user) {
		try {
			dao.save(user);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	public List<User> search(String q) {
		return dao.search(q);
	}
	
	

	public boolean existeComNome(String username) {
		if (username != null && username != "") {
			return dao.existsByUsername(username);
		}
		return false;
	}
}

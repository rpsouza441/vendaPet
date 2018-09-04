package br.com.lojapet.persistence.repository.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.lojapet.model.User;
import br.com.lojapet.persistence.service.UserService;

@Repository
public class UserUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	// TODO Conferir com a logica de login e senha
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user= userService.getUserByUsername(email);

		if (user == null) {
			throw new RuntimeException("O usuário " + email + " não foi encontrado");
		}

		return user;

	}

}

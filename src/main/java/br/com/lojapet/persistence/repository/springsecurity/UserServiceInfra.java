package br.com.lojapet.persistence.repository.springsecurity;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.lojapet.model.User;
import br.com.lojapet.persistence.service.UserService;

@Service
public class UserServiceInfra implements ApplicationListener<AuthenticationSuccessEvent> {
	@Autowired
	private UserService userService;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		String userName = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
		User user = userService.getUserByUsername(userName);
		user.setUltimoLogin(Calendar.getInstance());
		
		userService.updateUser(user);
		
	}
}

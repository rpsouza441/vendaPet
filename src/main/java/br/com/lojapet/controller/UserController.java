package br.com.lojapet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.lojapet.model.User;
import br.com.lojapet.persistence.service.UserService;

@Controller
@RequestMapping(value = "/usuario")
public class UserController {

	@Autowired
	protected UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	 @RequestMapping( method = RequestMethod.GET)
	 public ModelAndView home(ModelAndView modelAndView) {
	 modelAndView = new ModelAndView("/user/lista_user");
	 modelAndView.addObject("users", userService.getAllUsers());
	 return modelAndView;
	 }

	@RequestMapping(value = "cadastro")
	public ModelAndView form(User user) {
		ModelAndView modelAndView = new ModelAndView("/user/cadastro_user");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravarUser(@Valid User user, BindingResult result) {
		if (usernameExist(user.getUsername())) {
			result.rejectValue("username", "field.mustBe.Unique");
		}
		
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return form(user);
		}
		
		
		User userEncoded = new User();
		userEncoded.setId(user.getId());
		userEncoded.setNome(user.getNome());
		userEncoded.setUsername(user.getUsername());
		userEncoded.setPassword(passwordEncoder.encode(user.getPassword()));
	     

		userService.saveUser(userEncoded);

		return new ModelAndView("redirect:/usuario/cadastro");

	}
	private boolean usernameExist(final String username) {
        User userByUsername = userService.getUserByUsername(username);
        System.out.println(userByUsername.getUsername());
        System.out.println(userByUsername);
        System.out.println(userByUsername.getUsername() != null);
		return userByUsername.getUsername() != null;
}

}

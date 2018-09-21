package br.com.lojapet.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.lojapet.model.Role;
import br.com.lojapet.model.User;
import br.com.lojapet.persistence.service.RoleService;
import br.com.lojapet.persistence.service.UserService;

@Controller
@RequestMapping(value = "/usuario")
public class UserController {

	@Autowired
	protected UserService userService;
	
	@Autowired
	protected RoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView lista(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/user/lista_user");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView listaComSearch(@RequestParam(value = "search", required = false) String q,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("/user/lista_user");

		List<User> users;
		String error = null;

		if (q == null) {
			users = userService.getAllUsers();
		} else {
			users = userService.search(q);

		}

		if (users.isEmpty() && q != null) {
			error = "error.empty";
		}
		modelAndView.addObject("error", error);
		modelAndView.addObject("users", users);
		return modelAndView;

	}

	@RequestMapping(value = "cadastro")
	public ModelAndView form(User user) {
		ModelAndView modelAndView = new ModelAndView("/user/cadastro_user");
		List<Role> authorities = roleService.getAllRoles();
		modelAndView.addObject("authorities", authorities);
		modelAndView.addObject("user", user);

		return modelAndView;
	}

	@RequestMapping(value = "cadastro", method = RequestMethod.POST)
	public ModelAndView gravarUser(@Valid User user, BindingResult result) {
		if(user.getId()==null) {
			if (userService.existeComNome(user.getUsername())) {
				result.rejectValue("username", "field.mustBe.Unique");
			}
		}
		if (user.getAuthorities().isEmpty()) {
			result.rejectValue("authorities", "field.NotEmpty");
		}

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return form(user);
		}
		for (Role r : user.getAuthorities()) {
			System.out.println(r.getAuthority());
		}
		User userEncoded = new User();
		userEncoded.setId(user.getId());
		userEncoded.setNome(user.getNome());
		userEncoded.setUsername(user.getUsername());
		userEncoded.setPassword(passwordEncoder.encode(user.getPassword()));
		userEncoded.setAuthorities(user.getAuthorities());
		userService.saveUser(userEncoded);

		return new ModelAndView("redirect:/usuario");

	}
	@RequestMapping(value = "/editarUser/{uuid}")
	public ModelAndView editar(@PathVariable(value = "uuid") UUID uuid) {
		User user= userService.getUserById(uuid);

		return form(user);

	}


}

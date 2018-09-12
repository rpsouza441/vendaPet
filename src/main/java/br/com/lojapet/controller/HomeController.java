package br.com.lojapet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public ModelAndView home(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("index");
		
		
		return modelAndView;
	}

	@RequestMapping(value = "/index")
	public ModelAndView index(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("index");
		return modelAndView;
	}

}

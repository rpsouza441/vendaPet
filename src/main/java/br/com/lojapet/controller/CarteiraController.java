//package br.com.lojapet.controller;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import br.com.lojapet.model.Carteira;
//import br.com.lojapet.persistence.service.CarteiraService;
//
//@Controller
//@RequestMapping(value = "/carteira")
//public class CarteiraController {
//
//	@Autowired
//	protected CarteiraService carteiraService;
//
//	 @RequestMapping( method = RequestMethod.GET)
//	 public ModelAndView home(ModelAndView modelAndView) {
//	 modelAndView = new ModelAndView("/carteira/lista_carteira");
//	 modelAndView.addObject("carteiras", carteiraService.getAllCarteiras());
//	 return modelAndView;
//	 }
//
//	@RequestMapping(value = "cadastro")
//	public ModelAndView form(Carteira carteira) {
//		ModelAndView modelAndView = new ModelAndView("/carteira/cadastro_carteira");
//		
//		return modelAndView;
//	}
////
//	@RequestMapping(method = RequestMethod.POST)
//	public ModelAndView gravarCarteira(@Valid Carteira carteira, BindingResult result) {
//		if (result.hasErrors()) {
//			System.out.println(result.getAllErrors());
//			return form(carteira);
//		}
//
//		carteiraService.saveCarteira(carteira);
//
//		return new ModelAndView("redirect:/carteira/cadastro");
//
//	}
//
//}

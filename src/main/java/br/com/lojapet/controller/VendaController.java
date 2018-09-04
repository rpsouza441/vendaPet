//package br.com.lojapet.controller;
//
//import java.util.List;
//import java.util.UUID;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.servlet.ModelAndView;
//
//import br.com.lojapet.model.Produto;
//import br.com.lojapet.model.Venda;
//import br.com.lojapet.persistence.service.CarteiraService;
//import br.com.lojapet.persistence.service.ProdutoService;
//import br.com.lojapet.persistence.service.VendaService;
//
//@Controller
//@RequestMapping(value = "/venda")
//public class VendaController {
//
//	@Autowired
//	protected VendaService vendaService;
//
//	@Autowired
//	protected CarteiraService carteiraService;
//
//	@Autowired
//	protected ProdutoService produtoService;
//	
//	
//
////	@RequestMapping(value = "fecharVenda")
////	public ModelAndView fecharVenda(Venda venda) {
////		ModelAndView modelAndView = new ModelAndView("/venda/finalizar_venda");
////		
////		
////		modelAndView.addObject("venda",venda);
////
////		return modelAndView;
////	}
//
////	@RequestMapping(value = "finalizarVenda")
////	public ModelAndView finalizarVenda(Venda venda) {
////		ModelAndView modelAndView = new ModelAndView("/venda/cadastro_venda");
////
////		return modelAndView;
////	}
//
//	
//	
//
//}

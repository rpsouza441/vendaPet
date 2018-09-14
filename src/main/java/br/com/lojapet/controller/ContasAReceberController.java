//package br.com.lojapet.controller;
//
//import java.util.List;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import br.com.lojapet.model.Pagamento;
//import br.com.lojapet.persistence.service.PagamentoService;
//
//@Controller
//@RequestMapping(value = "/contaAReceber")
//public class ContasAReceberController {
//
//
//@Autowired
//private PagamentoService pagamentoService;
//
//	@RequestMapping(method = RequestMethod.GET)
//	public ModelAndView home(ModelAndView modelAndView) {
//		modelAndView = new ModelAndView("/conta/receber/lista");
//		return modelAndView;
//	}
//
//	@RequestMapping(method = RequestMethod.POST)
//	public ModelAndView listaComSearch(@RequestParam(value = "search", required = false) String q,
//			RedirectAttributes redirectAttributes) {
//		ModelAndView modelAndView = new ModelAndView("/conta-a-receber/lista_conta_receber");
//		List<Pagamento> contas;
//		String error = null;
//
//		if (q == null) {
//			contas = despesaService.getAllDespesas();
//		} else {
//			contas = despesaService.search(q);
//
//		}
//
//		if (contas.isEmpty() && q != null) {
//			error = "error.empty";
//		}
//		modelAndView.addObject("error", error);
//		modelAndView.addObject("contas", contas);
//		return modelAndView;
//
//	}
//
////	@RequestMapping(value = "/cadastro")
////	public ModelAndView form(DespesaAPagar despesaAPagar) {
////		ModelAndView modelAndView = new ModelAndView("/conta-a-receber/cadastro_conta_receber");
////		modelAndView.addObject("despesaAPagar", despesaAPagar);
////
////		return modelAndView;
////	}
//
////	@RequestMapping(value = "gravarDespesa", method = RequestMethod.POST)
////	public ModelAndView gravarDespesa(@Valid DespesaAPagar despesaAPagar, BindingResult result) {
////
////		if (result.hasErrors()) {
////			System.out.println("Erros: " + result.getAllErrors());
////			return form(despesaAPagar);
////		}
////
////		despesaAPagar.setCarteira(retornaCarteiraPrincipal());
////		DespesaAPagarBuilder builder = new DespesaAPagarBuilder(despesaAPagar);
////
////		List<DespesaAPagar> despesas = builder.constroi();
////		despesaService.saveDespesa(despesas);
////
////		return new ModelAndView("redirect:/despesa/cadastro");
////
////	}
//
////	@RequestMapping(value = "/editarDespesa/{uuid}")
////	public ModelAndView editar(@PathVariable(value = "uuid") UUID uuid) {
////		DespesaAPagar despesa = despesaService.getDespesaById(uuid);
////
////		return form(despesa);
////
////	}
//
//	@RequestMapping(value = "/excluirDespesa/{uuid}")
//	public ModelAndView excluirDespesa(@PathVariable(value = "uuid") UUID uuid) {
////		despesaService.deleteDespesa(uuid);
//
//		return new ModelAndView("redirect:/despesa");
//
//	}
//
//
//}

//package br.com.lojapet.controller;
//
//import java.util.List;
//import java.util.UUID;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import br.com.lojapet.builder.DespesaAPagarBuilder;
//import br.com.lojapet.model.Carteira;
//import br.com.lojapet.model.DespesaAPagar;
//import br.com.lojapet.persistence.service.CarteiraService;
//import br.com.lojapet.persistence.service.DespesaService;
//
//@Controller
//@RequestMapping(value = "/despesa")
//public class DespesaAPagarController {
//
//	@Autowired
//	protected DespesaService despesaService;
//
//	@Autowired
//	protected CarteiraService carteiraService;
//
//	@RequestMapping(method = RequestMethod.GET)
//	public ModelAndView home(ModelAndView modelAndView) {
//		modelAndView = new ModelAndView("/despesa-a-pagar/lista_despesa");
//		return modelAndView;
//	}
//
//	@RequestMapping(method = RequestMethod.POST)
//	public ModelAndView listaComSearch(@RequestParam(value = "search", required = false) String q,
//			RedirectAttributes redirectAttributes) {
//		ModelAndView modelAndView = new ModelAndView("/despesa-a-pagar/lista_despesa");
//		List<DespesaAPagar> despesas;
//		String error = null;
//
//		if (q == null) {
//			despesas = despesaService.getAllDespesas();
//		} else {
//			despesas = despesaService.search(q);
//
//		}
//
//		if (despesas.isEmpty() && q != null) {
//			error = "error.empty";
//		}
//		modelAndView.addObject("error", error);
//		modelAndView.addObject("despesas", despesas);
//		return modelAndView;
//
//	}
//
//	@RequestMapping(value = "/cadastro")
//	public ModelAndView form(DespesaAPagar despesaAPagar) {
//		ModelAndView modelAndView = new ModelAndView("/despesa-a-pagar/cadastro_despesasapagar");
//		modelAndView.addObject("despesaAPagar", despesaAPagar);
//
//		return modelAndView;
//	}
//
//	@RequestMapping(value = "/editar")
//	public ModelAndView editar(DespesaAPagar despesaAPagar) {
//		ModelAndView modelAndView = new ModelAndView("/despesa-a-pagar/edit_despesasapagar");
//		System.out.println("Editar "+despesaAPagar.getId());
//		modelAndView.addObject("despesaAPagar", despesaAPagar);
//
//		return modelAndView;
//	}
//
//	@RequestMapping(value = "gravarDespesa", method = RequestMethod.POST)
//	public ModelAndView gravarDespesa(@Valid DespesaAPagar despesaAPagar, BindingResult result) {
//
//		if (result.hasErrors()) {
//			return form(despesaAPagar);
//		}
//
//		despesaAPagar.setCarteira(retornaCarteiraPrincipal());
//		DespesaAPagarBuilder builder = new DespesaAPagarBuilder(despesaAPagar);
//
//		List<DespesaAPagar> despesas = builder.constroi();
//		despesaService.saveDespesa(despesas);
//
//		return new ModelAndView("redirect:/despesa/");
//
//	}
//	
//	@RequestMapping(value = "gravarEditarDespesa", method = RequestMethod.POST)
//	public ModelAndView gravarEditarDespesa(@Valid DespesaAPagar despesaAPagar, BindingResult result) {
//
//		if (result.hasErrors()) {
//			return editar(despesaAPagar);
//		}
//		System.out.println("Salvar "+despesaAPagar.getId());
//
//		despesaService.updateDespesa(despesaAPagar);
//
//		return new ModelAndView("redirect:/despesa/");
//
//	}
//
//
//	@RequestMapping(value = "/editarDespesa/{uuid}")
//	public ModelAndView editar(@PathVariable(value = "uuid") UUID uuid) {
//		DespesaAPagar despesa = despesaService.getDespesaById(uuid);
//		System.out.println(uuid);
//		System.out.println(despesa.getNome());
//		return editar(despesa);
//
//	}
//
//	@RequestMapping(value = "/excluirDespesa/{uuid}")
//	public ModelAndView excluirDespesa(@PathVariable(value = "uuid") UUID uuid) {
//		despesaService.deleteDespesa(uuid);
//
//		return new ModelAndView("redirect:/despesa");
//
//	}
//
//	private Carteira retornaCarteiraPrincipal() {
//		return carteiraService.getCarteiraByNome("Principal");
//	}
//
//}

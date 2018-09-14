package br.com.lojapet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/ContasAPagar")
public class ContasAPagarController {

//
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
//		return modelAndView;
//
//	}
//
//	@RequestMapping(value = "/cadastro")
//	public ModelAndView form(DespesaAPagar despesaAPagar) {
//		ModelAndView modelAndView = new ModelAndView("/despesa-a-pagar/cadastro_despesasapagar");
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


}

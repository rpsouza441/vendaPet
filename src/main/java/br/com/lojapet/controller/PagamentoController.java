package br.com.lojapet.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.lojapet.model.Grupo;
import br.com.lojapet.model.Pagamento;
import br.com.lojapet.persistence.service.PagamentoService;

@Controller
@RequestMapping(value = "/conta")
public class PagamentoController {

	@Autowired
	protected PagamentoService pagamentoService;

	@RequestMapping(value="recebida",method = RequestMethod.GET)
	public ModelAndView listaRecebida(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/conta/recebida/lista");
		
		return modelAndView;
	}
	
	@RequestMapping(value="recebida", method = RequestMethod.POST)
	public ModelAndView listaRecebidaComSearch(
			@RequestParam(value = "startWith", required = false)
			@DateTimeFormat(pattern = "dd/MM/yyyy") 
			Calendar startWith,
			@RequestParam(value = "endWith", required = false) 
			@DateTimeFormat(pattern = "dd/MM/yyyy") 
			Calendar endWith,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("/conta/recebida/lista");

		List<Pagamento> contasPagas;
		String error = null;

		if (startWith == null) {
			contasPagas = pagamentoService.getAllContasRecebidas();
		} else {
			contasPagas = pagamentoService.findContasRecebidasPorDataVencimentoBetween(startWith, endWith);

		}

		if (contasPagas.isEmpty()) {
			error = "error.empty";
		}
		modelAndView.addObject("error", error);
		modelAndView.addObject("contas", contasPagas);
		return modelAndView;

	}

	
	@RequestMapping(value="paga",method = RequestMethod.GET)
	public ModelAndView listaPaga(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/conta/paga/lista");
		return modelAndView;
	}

	@RequestMapping(value="paga", method = RequestMethod.POST)
	public ModelAndView listaPagaComSearch(
			@RequestParam(value = "startWith", required = false)
			@DateTimeFormat(pattern = "dd/MM/yyyy") 
			Calendar startWith,
			@RequestParam(value = "endWith", required = false) 
			@DateTimeFormat(pattern = "dd/MM/yyyy") 
			Calendar endWith,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("/conta/paga/lista");

		List<Pagamento> contasPagas;
		String error = null;

		if (startWith == null) {
			contasPagas = pagamentoService.getAllContasPagas();
		} else {
			contasPagas = pagamentoService.findContasPagasPorDataVencimentoBetween(startWith, endWith);

		}

		if (contasPagas.isEmpty()) {
			error = "error.empty";
		}
		modelAndView.addObject("error", error);
		modelAndView.addObject("contas", contasPagas);
		return modelAndView;

	}

	@RequestMapping(value = "cadastro")
	public ModelAndView form(Grupo grupo) {
		ModelAndView modelAndView = new ModelAndView("/grupo/cadastro_grupo");
		modelAndView.addObject("grupo", grupo);
		return modelAndView;
	}

//	@RequestMapping(value = "gravar", method = RequestMethod.POST)
//	public ModelAndView gravar(@Valid Grupo grupo, BindingResult result) {
//		if (result.hasErrors()) {
//			System.out.println(result.getAllErrors());
//			return form(grupo);
//		}
//
//		grupoService.saveGrupo(grupo);
//
//		return new ModelAndView("redirect:/grupo/cadastro");
//
//	}
	
//	
//	@RequestMapping(value = "/editarGrupo/{uuid}")
//	public ModelAndView editar(@PathVariable(value = "uuid") UUID uuid) {
//		Grupo grupo= grupoService.getGrupoById(uuid);
//
//		return form(grupo);
//
//	}

//	@RequestMapping(value = "/excluirGrupo/{uuid}")
//	public ModelAndView excluirCliente(@PathVariable(value = "uuid") UUID uuid) {
//		grupoService.deleteGrupo(uuid);
//
//		return new ModelAndView("redirect:/grupo");
//
//	}

}

package br.com.lojapet.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.lojapet.model.Caixa;
import br.com.lojapet.persistence.service.CaixaService;

@Controller
public class RelatorioCaixaController {

	@Autowired
	protected CaixaService caixaService;


	@RequestMapping(value = "/relatorio/caixa")
	public ModelAndView relatorioCaixa(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/relatorio/caixa/relatorio_caixa");

		return modelAndView;
	}

	@RequestMapping(value = "/relatorio/caixa", method = RequestMethod.POST)
	public ModelAndView relatorioCaixaComSearch(
			@RequestParam(value = "startWith", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Calendar startWith,
			@RequestParam(value = "endWith", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Calendar endWith,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("/relatorio/caixa/relatorio_caixa");
		List<Caixa> listaCaixa = new ArrayList<>();

		String error = null;

		if (startWith == null) {
			startWith = Calendar.getInstance();
			startWith.add(Calendar.MONTH, -12);
			endWith = Calendar.getInstance();
		}
		listaCaixa = caixaService.findCaixasPorDataFechamentoBetween(startWith, endWith);

		if (listaCaixa.isEmpty()) {
			error = "error.empty";
		}
		modelAndView.addObject("inicio", startWith);
		modelAndView.addObject("fim", endWith);
		modelAndView.addObject("error", error);
		modelAndView.addObject("caixas", listaCaixa);
		return modelAndView;

	}
	
	
	@RequestMapping(value = "/relatorio/caixa/mostrar/{uuid}")
	public ModelAndView editar(@PathVariable(value = "uuid") UUID uuid) {
		Caixa caixa= caixaService.getCaixaById(uuid);

		return form(caixa);

	}
	
	@RequestMapping(value = "/relatorio/caixa/mostrar")
	public ModelAndView form(Caixa caixa) {
		ModelAndView modelAndView = new ModelAndView("/relatorio/caixa/mostrar_caixa");

		modelAndView.addObject("caixa", caixa);

		return modelAndView;
	}
	
	
	
	

}

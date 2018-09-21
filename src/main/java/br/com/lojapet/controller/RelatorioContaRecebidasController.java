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

import br.com.lojapet.model.Pagamento;
import br.com.lojapet.persistence.service.PagamentoService;

@Controller
public class RelatorioContaRecebidasController {

	@Autowired
	protected PagamentoService pagamentoService;


	@RequestMapping(value = "/relatorio/contasRecebidas")
	public ModelAndView relatorioRecebidas(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/relatorio/conta/relatorio_recebidas");

		return modelAndView;
	}

	@RequestMapping(value = "/relatorio/contasRecebidas", method = RequestMethod.POST)
	public ModelAndView relatorioRecebidasComSearch(
			@RequestParam(value = "startWith", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Calendar startWith,
			@RequestParam(value = "endWith", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Calendar endWith,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("/relatorio/conta/relatorio_recebidas");
		List<Pagamento> contasPagas;
		
		String error = null;

		if (startWith == null) {
			startWith = Calendar.getInstance();
			startWith.add(Calendar.MONTH, -12);
			endWith = Calendar.getInstance();
		}
		contasPagas = pagamentoService.findContasRecebidasPorDataVencimentoPagasBetween(startWith, endWith);

		if (contasPagas.isEmpty()) {
			error = "error.empty";
		}
		modelAndView.addObject("inicio", startWith);
		modelAndView.addObject("fim", endWith);
		modelAndView.addObject("error", error);
		modelAndView.addObject("contas", contasPagas);
		return modelAndView;

	}
	
	
	
	
	
	

}

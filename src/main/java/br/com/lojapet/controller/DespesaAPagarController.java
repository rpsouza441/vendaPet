package br.com.lojapet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.lojapet.builder.DespesaAPagarBuilder;
import br.com.lojapet.model.Carteira;
import br.com.lojapet.model.Cliente;
import br.com.lojapet.model.DespesaAPagar;
import br.com.lojapet.persistence.service.CarteiraService;
import br.com.lojapet.persistence.service.DespesaService;

@Controller
@RequestMapping(value = "/despesa")
public class DespesaAPagarController {

	@Autowired
	protected DespesaService despesaService;

	@Autowired
	protected CarteiraService carteiraService;

	private List<Carteira> carteiras = new ArrayList<>();

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/despesa-a-pagar/lista_despesa");
		modelAndView.addObject("despesas", despesaService.getAllDespesas());
		return modelAndView;
	}
	


	@RequestMapping(value = "/cadastro")
	public ModelAndView form(DespesaAPagar despesaAPagar) {
		ModelAndView modelAndView = new ModelAndView("/despesa-a-pagar/cadastro_despesasapagar");
			carteiras = carteiraService.getAllCarteiras();
		modelAndView.addObject("despesaAPagar", despesaAPagar);
		modelAndView.addObject("carteiras", carteiras);

		return modelAndView;
	}
	

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravarDespesa(@Valid DespesaAPagar despesaAPagar, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("Erros: "+result.getAllErrors());
			return form(despesaAPagar);
		}

		DespesaAPagarBuilder builder = new DespesaAPagarBuilder(despesaAPagar);

		List<DespesaAPagar> despesas = builder.constroi();
		despesaService.saveDespesa(despesas);

		return new ModelAndView("redirect:/despesa/cadastro");

	}

	@RequestMapping(value = "/editarDespesa/{uuid}")
	public ModelAndView editar(@PathVariable(value = "uuid") UUID uuid) {
		DespesaAPagar despesa = despesaService.getDespesaById(uuid);

		return form(despesa);

	}

	@RequestMapping(value = "/excluirDespesa/{uuid}")
	public ModelAndView excluirDespesa(@PathVariable(value = "uuid") UUID uuid) {
		despesaService.deleteDespesa(uuid);

		return new ModelAndView("redirect:/despesa");

	}

}

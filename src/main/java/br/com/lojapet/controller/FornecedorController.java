package br.com.lojapet.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.lojapet.model.Fornecedor;
import br.com.lojapet.persistence.service.FornecedorService;

@Controller
@RequestMapping(value = "/fornecedor")
public class FornecedorController {

	@Autowired
	protected FornecedorService fornecedorService;


	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView lista(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/fornecedor/lista_fornecedor");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView listaComSearch(@RequestParam(value = "search", required = false) String q,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView  = new ModelAndView("/fornecedor/lista_fornecedor");
		List<Fornecedor> fornecedores;
		String error = null;

		if (q == null) {
			fornecedores = fornecedorService.getAllFornecedors();
		} else {
			fornecedores = fornecedorService.search(q);

		}

		if (fornecedores.isEmpty() && q != null) {
			error = "error.empty";
		}
		modelAndView.addObject("error", error);
		modelAndView.addObject("fornecedores", fornecedores);
		return modelAndView;

	}

	@RequestMapping(value = "/cadastro")
	public ModelAndView form(Fornecedor fornecedor) {
		ModelAndView modelAndView = new ModelAndView("/fornecedor/cadastro_fornecedor");
		modelAndView.addObject("fornecedor", fornecedor);

		return modelAndView;
	}

	@RequestMapping(value="gravarFornecedor", method = RequestMethod.POST)
	public ModelAndView gravarFornecedor(@Valid Fornecedor fornecedor, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return form(fornecedor);
		}

		fornecedorService.saveFornecedor(fornecedor);

		return new ModelAndView("redirect:/fornecedor/cadastro");

	}

	@RequestMapping(value = "/editarFornecedor/{uuid}")
	public ModelAndView editar(@PathVariable(value = "uuid") UUID uuid) {
		Fornecedor fornecedor = fornecedorService.getFornecedorById(uuid);

		return form(fornecedor);

	}

	@RequestMapping(value = "/excluirFornecedor/{uuid}")
	public ModelAndView excluirFornecedor(@PathVariable(value = "uuid") UUID uuid) {
		fornecedorService.deleteFornecedor(uuid);

		return new ModelAndView("redirect:/fornecedor");

	}

}

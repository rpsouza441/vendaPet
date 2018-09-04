package br.com.lojapet.controller;

import java.math.BigDecimal;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.lojapet.infra.XMLExtractor;
import br.com.lojapet.model.Carteira;
import br.com.lojapet.model.Compra;
import br.com.lojapet.model.Fornecedor;
import br.com.lojapet.model.Produto;
import br.com.lojapet.persistence.service.CarteiraService;
import br.com.lojapet.persistence.service.CompraService;
import br.com.lojapet.persistence.service.FornecedorService;

@Controller
@RequestMapping(value = "/compra")
public class CompraRealizadaController {

	@Autowired
	protected CompraService compraService;

	@Autowired
	protected CarteiraService carteiraService;
	
	@Autowired
	protected FornecedorService fornecedorService;
	
	@Autowired
	private XMLExtractor xmlExtractor;

	private List<Carteira> carteiras = new ArrayList<>();
	private List<Fornecedor> fornecedores = new ArrayList<>();

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/compra-realizada/lista_compra");
		modelAndView.addObject("compras", compraService.getAllCompras());
		return modelAndView;
	}
	


	@RequestMapping(value = "/cadastro")
	public ModelAndView form(Compra compraAPagar) {
		ModelAndView modelAndView = new ModelAndView("/compra-realizada/cadastro_comprarealizada");
			carteiras = carteiraService.getAllCarteiras();
			fornecedores = fornecedorService.getAllFornecedors();
		modelAndView.addObject("compraAPagar", compraAPagar);
		modelAndView.addObject("carteiras", carteiras);
		modelAndView.addObject("fornecedores", fornecedores);

		return modelAndView;
	}

	

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ModelAndView gravarCompra(MultipartFile endereco, @Valid Compra compra, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (endereco.isEmpty()) {
			result.rejectValue("enderecoPath", "field.required");
		}
		if (result.hasErrors()) {
			System.out.println("Erros: "+result.getAllErrors());
			return form(compra);
		}
		List<Produto> produtos = xmlExtractor.read(endereco);
		compraService.saveCompra(compra, compra.getFornecedor().getId(), produtos);

		return new ModelAndView("redirect:/compra/cadastro");

	}

	@RequestMapping(value = "/editarCompra/{uuid}")
	public ModelAndView editar(@PathVariable(value = "uuid") UUID uuid) {
		Compra compra = compraService.getCompraById(uuid);

		return form(compra);

	}

	@RequestMapping(value = "/excluirCompra/{uuid}")
	public ModelAndView excluirCompra(@PathVariable(value = "uuid") UUID uuid) {
		compraService.deleteCompra(uuid);

		return new ModelAndView("redirect:/compra");

	}

}

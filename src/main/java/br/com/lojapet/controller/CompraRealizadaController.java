package br.com.lojapet.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.lojapet.facade.CompraFacade;
import br.com.lojapet.facade.XmlFacade;
import br.com.lojapet.infra.XMLExtractor;
import br.com.lojapet.model.Compra;
import br.com.lojapet.model.CompraForm;
import br.com.lojapet.model.Fornecedor;
import br.com.lojapet.model.Produto;
import br.com.lojapet.model.User;
import br.com.lojapet.persistence.service.CompraService;
import br.com.lojapet.persistence.service.FornecedorService;
import br.com.lojapet.persistence.service.ProdutoService;
import br.com.lojapet.persistence.service.UserService;

@Controller
@RequestMapping(value = "/compra")
public class CompraRealizadaController {
	@Autowired
	private UserService userService;

	@Autowired
	protected CompraService compraService;

	@Autowired
	protected FornecedorService fornecedorService;

	@Autowired
	protected ProdutoService produtoService;

	@Autowired
	private XMLExtractor xmlExtractor;

	private List<Fornecedor> fornecedores = new ArrayList<>();
	// @DateTimeFormat(pattern="dd/MM/yyyy")
	// private Calendar startWith;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/compra-realizada/lista_compra");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView listaComSearch(
			@RequestParam(value = "startWith", required = false)
			@DateTimeFormat(pattern = "dd/MM/yyyy") 
			Calendar startWith,
			@RequestParam(value = "endWith", required = false) 
			@DateTimeFormat(pattern = "dd/MM/yyyy") 
			Calendar endWith,
			RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView("/compra-realizada/lista_compra");
		List<Compra> compras;
		String error = null;

		if (startWith == null) {
			startWith = Calendar.getInstance();
			startWith.add(Calendar.MONTH, -12);
			endWith = Calendar.getInstance();
		}
			compras = compraService.findCompraBetween(startWith, endWith);

		

		if (compras.isEmpty() && startWith != null) {
			error = "error.empty";
		}
		modelAndView.addObject("compras", compras);
		modelAndView.addObject("error", error);
		return modelAndView;

	}

	@RequestMapping(value = "/entradaNF")
	public ModelAndView entradaNF(CompraForm compraForm) {
		ModelAndView modelAndView = new ModelAndView("/compra-realizada/inserir_xml");
		fornecedores = fornecedorService.getAllFornecedors();
		modelAndView.addObject("compra", compraForm);

		return modelAndView;
	}

	@RequestMapping(value = "/gravarNF", method = RequestMethod.POST)
	public ModelAndView gravarNF(MultipartFile endereco, @Valid CompraForm compraForm, BindingResult result) {

		if (endereco.isEmpty()) {
			result.rejectValue("enderecoPath", "field.required");
		}
		if (result.hasErrors()) {
			return entradaNF(compraForm);
		}
		XmlFacade xmlFacade = xmlExtractor.read(endereco);

		CompraFacade compraFacade = new CompraFacade(xmlFacade, produtoService, compraService, fornecedorService);
		xmlFacade = compraFacade.persisteProdutoFornecedor();
		xmlFacade.geraTotalCompra();
		
		Compra compraXML = xmlFacade.getCompra();

		compraXML.montaCompra();

		compraXML.setListaProduto(xmlFacade.getProdutos());
		compraXML.setFornecedor(xmlFacade.getFornecedor());
		User logado = retornaUsuarioLogado();
		compraXML.setUser(logado);

		Compra compraPersistida = compraService.saveCompraWithReturn(compraXML);

		logado.addCompra(compraPersistida);
		return new ModelAndView("redirect:/compra/");

	}

	@RequestMapping(value = "/cadastrar")
	public ModelAndView form(Compra compraAPagar) {
		ModelAndView modelAndView = new ModelAndView("/compra-realizada/inserir_xml");
		fornecedores = fornecedorService.getAllFornecedors();

		return modelAndView;
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ModelAndView gravarCompra(MultipartFile endereco, @Valid Compra compra, BindingResult result,
			RedirectAttributes redirectAttributes) {

		XmlFacade xmlFacade = xmlExtractor.read(endereco);

		Fornecedor fornecedorPersistido = fornecedorService.saveFornecedorWithReturn(xmlFacade.getFornecedor());

		xmlFacade.atrelaFornecedorAosProdutos(fornecedorPersistido);

		List<Produto> listaProdutoPersistido = produtoService.saveProdutoWithReturn(xmlFacade.getProdutos());

		Compra compraXML = xmlFacade.getCompra();

		compraXML.montaCompra();

		compraXML.setListaProduto(listaProdutoPersistido);
		compraXML.setFornecedor(fornecedorPersistido);
		compraXML.setUser(retornaUsuarioLogado());

		compraService.saveCompraWithReturn(compraXML);

		return new ModelAndView("redirect:/compra/cadastro");

	}

	@RequestMapping(value = "/visualizarCompra/{uuid}")
	public ModelAndView visualizarCompra(@PathVariable(value = "uuid") UUID uuid) {
		Compra compra = compraService.getCompraById(uuid);
		ModelAndView modelAndView = new ModelAndView("/compra-realizada/show_compra");
		modelAndView.addObject("compra", compra);
		
		
		
		return modelAndView;
		
	}

	@RequestMapping(value = "/excluirCompra/{uuid}")
	public ModelAndView excluirCompra(@PathVariable(value = "uuid") UUID uuid) {
		compraService.deleteCompra(uuid);

		return new ModelAndView("redirect:/compra");

	}

	private User retornaUsuarioLogado() {
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.getUserById(principal.getId());
	}

}

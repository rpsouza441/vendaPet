package br.com.lojapet.controller;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.lojapet.model.CarrinhoCompra;
import br.com.lojapet.model.CarrinhoItem;
import br.com.lojapet.model.Compra;
import br.com.lojapet.model.FormaDePagamento;
import br.com.lojapet.model.Fornecedor;
import br.com.lojapet.model.Produto;
import br.com.lojapet.model.User;
import br.com.lojapet.persistence.service.CaixaService;
import br.com.lojapet.persistence.service.CompraService;
import br.com.lojapet.persistence.service.FornecedorService;
import br.com.lojapet.persistence.service.MovimentoDeCaixaService;
import br.com.lojapet.persistence.service.ProdutoService;
import br.com.lojapet.persistence.service.UserService;

@Controller
@RequestMapping(value = "/compra")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarrinhoCompraController {

	@Autowired
	private UserService userService;

	@Autowired
	protected CompraService compraService;

	@Autowired
	protected ProdutoService produtoService;

	@Autowired
	private CarrinhoCompra carrinho;

	@Autowired
	private CaixaService caixaService;

	@Autowired
	private MovimentoDeCaixaService movimentoDeCaixaService;

	@Autowired
	private FornecedorService fornecedorService;

	@RequestMapping(value = "lista", method = RequestMethod.GET)
	public ModelAndView home(ModelAndView modelAndView) {
		modelAndView = redirecionaSeCarrinhoEstaVazio("/compra-realizada/resumo_carrinho",
				"redirect:/compra/procurarProduto");

		return modelAndView;
	}

	@RequestMapping(value = "/adicionaNoCarrinho/{uuid}")
	public ModelAndView adicionarProdutoNoCarrinho(@PathVariable(value = "uuid") UUID uuid) {

		CarrinhoItem item = createItem(uuid);
		carrinho.add(item);
		return new ModelAndView("redirect:/compra/lista");

	}

	@RequestMapping("/removerDoCarrinho/{id}")
	public ModelAndView remover(@PathVariable("id") UUID produtoId) {
		System.out.println("removido");
		System.out.println(produtoId);
		carrinho.remover(createItem(produtoId));
		// carrinho.remover(produtoId, tipoPreco);
		return new ModelAndView("redirect:/carrinho");
	}

	// gera pagina finalizar
	@RequestMapping(value = "fecharCompra")
	public ModelAndView fecharCompra(Compra compra) {
		ModelAndView modelAndView = redirecionaSeCarrinhoEstaVazio("/compra-realizada/finalizar_compra",
				"redirect:/compra/procurarProduto");
		modelAndView.addObject("formaDePagamento", FormaDePagamento.values());

		compra.montaCompra(extraiListaDeProdutosEQuantidadeDoCarrinho());
		compra.setParcelas(1);

		modelAndView.addObject("compra", compra);
		modelAndView.addObject("fornecedores", fornecedorService.getAllFornecedors());

		return modelAndView;
	}

	// atualiza a pagina de fechar
	@RequestMapping(value = "fecharVenda", method = RequestMethod.POST)
	public ModelAndView atualizaFecharCompra(Compra compra) {
		ModelAndView modelAndView = redirecionaSeCarrinhoEstaVazio("/compra-realizada/finalizar_compra",
				"redirect:/compra/procurarProduto");
		modelAndView.addObject("formaDePagamento", FormaDePagamento.values());
		modelAndView.addObject("fornecedores", fornecedorService.getAllFornecedors());
		modelAndView.addObject("compra", compra);

		return modelAndView;
	}

	@RequestMapping(value = "/limparCarrinho", method = RequestMethod.GET)
	public ModelAndView limparCarrinho() {
		carrinho.limpaCarrinho();
		return new ModelAndView("redirect:/compra");

	}

	@RequestMapping(value = "finalizarCompra")
	public ModelAndView finalizarCompra(@Valid Compra compra, BindingResult result, HttpServletRequest request) {

		ModelAndView modelAndView = redirecionaSeCarrinhoEstaVazio("redirect:/", "redirect:/compra/procurarProduto");
		if (carrinho.getItens().isEmpty()) {
			return modelAndView;
		}
		
		if (request.getParameter("gerarParcelas") != null) {
			compra.gerarParcelas();
			return atualizaFecharCompra(compra);

		}
		
		if (compra.getFornecedor() == null) {
			result.rejectValue("fornecedor", "field.required");
		}
		
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return atualizaFecharCompra(compra);
		}

		persisteCompra(compra);
		carrinho.limpaCarrinho();

		return modelAndView;
	}



	@RequestMapping(value = "/procurarProduto")
	public ModelAndView procurarProduto(@RequestParam(value = "search", required = false) String q,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("/produto/procurar_produto_compra");
		List<Produto> produtos = produtoService.search(q);
		String error = null;
		if (produtos.isEmpty() && q != null) {
			error = "error.empty";
		}
		modelAndView.addObject("error", error);
		modelAndView.addObject("produtos", produtos);
		return modelAndView;

	}

	private void persisteCompra(Compra compraASerPersistida) {

		User logado = retornaUsuarioLogado();

		compraASerPersistida.preparaCompraParaPersistir(logado);

		Compra compraPersistida = compraService.saveCompraWithReturn(compraASerPersistida);

		adicionaQuantidadeNoEstoqueAlteraCustoELigaVendaCom(compraPersistida, logado);
	}
	
	

	private void adicionaQuantidadeNoEstoqueAlteraCustoELigaVendaCom(Compra compraPersistida, User logado) {
		logado.addCompra(compraPersistida);
		Fornecedor fornecedorById = fornecedorService.getFornecedorById(compraPersistida.getFornecedor().getId());
		fornecedorById.addCompra(compraPersistida);
		logado.addCompra(compraPersistida);
		
		
		
		fornecedorService.updateFornecedor(fornecedorById);
		userService.updateUser(logado);
		produtoService.novaCompra(compraPersistida.getListaProduto());
	}

	private List<Produto> extraiListaDeProdutosEQuantidadeDoCarrinho() {
		Collection<CarrinhoItem> itens = carrinho.getItens();
		List<Produto> produtos = itens.stream().map(CarrinhoItem::getProduto).collect(Collectors.toList());
		return produtos;
	}

	private ModelAndView redirecionaSeCarrinhoEstaVazio(String urlSePreenchido, String urlSeVazio) {
		ModelAndView modelAndView;
		if (carrinho.getItens().isEmpty()) {
			modelAndView = new ModelAndView(urlSeVazio);
		} else {
			modelAndView = new ModelAndView(urlSePreenchido);

		}
		return modelAndView;
	}

	private CarrinhoItem createItem(UUID productId) {
		Produto produto = produtoService.getProdutoById(productId);
		CarrinhoItem item = new CarrinhoItem(produto);
		return item;
	}

	private User retornaUsuarioLogado() {
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.getUserById(principal.getId());
	}

}

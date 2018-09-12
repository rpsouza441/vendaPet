package br.com.lojapet.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.com.lojapet.model.Caixa;
import br.com.lojapet.model.Carrinho;
import br.com.lojapet.model.CarrinhoItem;
import br.com.lojapet.model.Cliente;
import br.com.lojapet.model.FormaDePagamento;
import br.com.lojapet.model.MovimentoDeCaixa;
import br.com.lojapet.model.OrigemMovimento;
import br.com.lojapet.model.Pagamento;
import br.com.lojapet.model.Produto;
import br.com.lojapet.model.TipoDeMovimentacao;
import br.com.lojapet.model.User;
import br.com.lojapet.model.Venda;
import br.com.lojapet.persistence.service.CaixaService;
import br.com.lojapet.persistence.service.ClienteService;
import br.com.lojapet.persistence.service.MovimentoDeCaixaService;
import br.com.lojapet.persistence.service.ProdutoService;
import br.com.lojapet.persistence.service.UserService;
import br.com.lojapet.persistence.service.VendaService;
import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;

@Controller
@RequestMapping(value = "/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarrinhoController {

	@Autowired
	private UserService userService;

	@Autowired
	protected VendaService vendaService;

	@Autowired
	protected ProdutoService produtoService;

	@Autowired
	private Carrinho carrinho;

	@Autowired
	private CaixaService caixaService;

	@Autowired
	private MovimentoDeCaixaService movimentoDeCaixaService;

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(ModelAndView modelAndView) {
		modelAndView = redirecionaSeCarrinhoEstaVazio("/carrinho/resumo_carrinho", "redirect:/produto/procurarProduto");

		return modelAndView;
	}

	@RequestMapping(value = "/adicionaNoCarrinho/{uuid}")
	public ModelAndView adicionarProdutoNoCarrinho(@PathVariable(value = "uuid") UUID uuid) {

		CarrinhoItem item = createItem(uuid);
		carrinho.add(item);
		return new ModelAndView("redirect:/carrinho");

	}

	@RequestMapping("/removerDoCarrinho/{id}")
	public ModelAndView remover(@PathVariable("id") UUID produtoId) {
		System.out.println("removido");
		System.out.println(produtoId);
		carrinho.remover(createItem(produtoId));
		// carrinho.remover(produtoId, tipoPreco);
		return new ModelAndView("redirect:/carrinho");
	}

	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public ModelAndView finalizar(
			// @AuthenticationPrincipal Usuario usuario,
			HttpServletRequest request) {

		// Sabendo que é para atualizar, você manda ele de volta para o carrinho. Se ele
		// não entrar aqui, é porque ele clicou em Finalizar.
		if (request.getParameter("atualizar") != null) {
			String[] quantidades = request.getParameterValues("quantidade");
			Iterator<CarrinhoItem> it = carrinho.getItens().iterator();

			int i = 0;
			while (it.hasNext()) {
				carrinho.updateQuantidade(it.next(), Integer.parseInt(quantidades[i]));
				i++;
			}
			return new ModelAndView("redirect:/carrinho");

		} else if (request.getParameter("orcamento") != null) {
			return new ModelAndView("redirect:/carrinho/gerarOrcamento");
			/*
			 * To-Do esta quebrado
			 */
		}

		return new ModelAndView("redirect:/carrinho/fecharVenda");

	}

	@RequestMapping(value = "/limparCarrinho", method = RequestMethod.GET)
	public ModelAndView limparCarrinho() {
		carrinho.limpaCarrinho();
		return new ModelAndView("redirect:/carrinho");

	}

	@RequestMapping(value = "fecharVenda")
	public ModelAndView fecharVenda(Venda venda) {
		ModelAndView modelAndView = redirecionaSeCarrinhoEstaVazio("/venda/finalizar_venda",
				"redirect:/produto/procurarProduto");
		modelAndView.addObject("formaDePagamento", FormaDePagamento.values());

		venda.montaVenda(carrinho.getValorTotal());
		venda.setParcelas(1);

		modelAndView.addObject("venda", venda);

		return modelAndView;
	}

	@RequestMapping(value = "fecharVenda", method = RequestMethod.POST)
	public ModelAndView atualizaFecharVenda(Venda venda) {
		ModelAndView modelAndView = redirecionaSeCarrinhoEstaVazio("/venda/finalizar_venda",
				"redirect:/produto/procurarProduto");
		modelAndView.addObject("formaDePagamento", FormaDePagamento.values());

		venda.montaVenda(carrinho.getValorTotal());

		modelAndView.addObject("venda", venda);

		return modelAndView;
	}

	@RequestMapping(value = "finalizarVenda")
	public ModelAndView finalizarVenda(Venda venda, BindingResult result, HttpServletRequest request) {

		ModelAndView modelAndView = redirecionaSeCarrinhoEstaVazio("redirect:/", "redirect:/produto/procurarProduto");
		if (carrinho.getItens().isEmpty()) {
			return modelAndView;
		}
		if (request.getParameter("gerarParcelas") != null) {
			venda.gerarParcelas();
			return atualizaFecharVenda(venda);

		}

		verificaSeClientePossuIDESeExiste(venda, result);

		if (result.hasErrors()) {
			return atualizaFecharVenda(venda);
		}

		persisteVenda(venda);
		carrinho.limpaCarrinho();

		return modelAndView;
	}

	private void verificaSeClientePossuIDESeExiste(Venda venda, BindingResult result) {
		if ((!(venda.getCliente().getNomeCompleto() == null || venda.getCliente().getNomeCompleto() == ""))
				&& venda.getCliente().getId() == null) {
			if (!clienteService.existsById(venda.getCliente().getId())) {
				result.rejectValue("cliente", "field.required");
			}
		}
	}
	//

	@RequestMapping(value = "search", method = RequestMethod.GET)
	@ResponseBody
	public List<String> search(HttpServletRequest request) {
		return clienteService.getClienteByNameLike(request.getParameter("term"));
	}

	@RequestMapping(value = "searchAjax", method = RequestMethod.GET)
	@ResponseBody
	public String searchAjax(HttpServletRequest request) {
		Gson gson = new Gson();

		return gson.toJson(clienteService.getListClienteByNameLike(request.getParameter("keyword")));
	}

	@RequestMapping(value = "gerarOrcamento")
	public ModelAndView gerarOrcamento(Venda venda) {
		ModelAndView modelAndView = redirecionaSeCarrinhoEstaVazio("/venda/gerar_orcamento",
				"redirect:/produto/procurarProduto");

		// montaVendaParaPaginaDeFinalizarVenda(venda);
		modelAndView.addObject("venda", venda);

		return modelAndView;
	}

	private void persisteVenda(Venda vendaASerPersistida) {
		Cliente cliente = null;
		if(vendaASerPersistida.getCliente().getId()!= null) {
			 cliente = clienteService.getClienteById(vendaASerPersistida.getCliente().getId());
		}

		List<Produto> produtos = extraiListaDeProdutosEQuantidadeDoCarrinho();

		Caixa caixaAberto = caixaService.getCaixaAberto();

		User logado = retornaUsuarioLogado();
		
		vendaASerPersistida.preparaVendaParaPersistir(produtos, logado, cliente);

		Venda vendaPersistida = vendaService.saveVendaWithReturn(vendaASerPersistida);

		removeQuantidadeDoEstoqueELigaVendaCom(caixaAberto, vendaPersistida, logado, cliente);
	}

	private void removeQuantidadeDoEstoqueELigaVendaCom(Caixa caixaAberto, Venda vendaPersistida,
			User logado, Cliente cliente) {
		resolveLigacoesCaixaMovimento(caixaAberto, vendaPersistida);
		logado.addVenda(vendaPersistida);
		
		System.out.println(vendaPersistida.getCliente());
		if(vendaPersistida.getCliente()!=null) {
			cliente.addVenda(vendaPersistida);
			clienteService.updateCliente(cliente);
		}
		
		userService.updateUser(logado);
		caixaService.updateCaixa(caixaAberto);
		produtoService.removeQuantidade(carrinho.getUuidEQuantidade());
	}

	private void resolveLigacoesCaixaMovimento(Caixa caixaAberto, Venda vendaPersistida) {
		List<MovimentoDeCaixa> movimentoEntrada = geraMovimentoDeCaixaDeVenda(vendaPersistida, caixaAberto);
		List<MovimentoDeCaixa> movimentoDeCaixaPersistido = movimentoDeCaixaService
				.saveMovimentoDeCaixaWithReturn(movimentoEntrada);
		caixaAberto.addListaMovimentacao(movimentoDeCaixaPersistido);
	}

	private List<MovimentoDeCaixa> geraMovimentoDeCaixaDeVenda(Venda v, Caixa caixaAberto) {
		List<MovimentoDeCaixa> listaMovimento = new ArrayList<MovimentoDeCaixa>();
		for (Pagamento p : v.getContaAReceber()) {
			System.out.println(p.getTotal());
			listaMovimento.add(MovimentoDeCaixa.builder()
					.dataHoraMovimento(v.getDataEmissao())
					.valor(p.getTotal())
					.observacao(v.getDadosCliente())
					.formaDePagamento(p.getFormaDePagamento())
					.origemMovimento(OrigemMovimento.VENDA)
					.tipoDeMovimentacao(TipoDeMovimentacao.ENTRADA)
					.user(v.getUser())
					.caixa(caixaAberto)
					.build());
		}
		

		return listaMovimento;
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

	// @RequestMapping(value = "search", method = RequestMethod.GET)
	// public @ResponseBody List<Cliente> getCliente(@RequestParam String
	// clienteNome) {
	// System.out.println("aqui");
	// return clienteService.getClienteByNameLike(clienteNome);
	//
	//
	// }
	//
	// @RequestMapping(value = "search", method = RequestMethod.GET)
	// @ResponseBody
	// public List<String> search(HttpServletRequest request) {
	// System.out.println("aqui");
	// ObjectMapper mapper = new ObjectMapper();
	// List<Cliente> list =
	// clienteService.getClienteByNameLike(request.getParameter("term"));
	// List<String> jsonRetorno = mapper.writeValueAsString(list);
	// return jsonRetorno;
	// }

	// @RequestMapping(value = "/book/search", method = { RequestMethod.GET })
	// public Collection<Book> list(@ModelAttribute("bookSearchCriteria")
	// BookSearchCriteria criteria) {
	// return this.bookstoreService.findBooks(criteria);
	// }

	// @RequestMapping(value = "json", method = RequestMethod.POST)
	// public @ResponseBody String save(@RequestBody String jsonString) {
	// ObjectMapper mapper = new ObjectMapper();
	// Venda obj = new Venda();
	// try {
	// obj = mapper.readValue(jsonString, Venda.class);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// List<Produto> listaProduto = new ArrayList<>();
	// Produto produto = new Produto();
	// produto.setNome("Produto 1");
	// produto.setValorVenda(BigDecimal.valueOf(7));
	// listaProduto.add(produto);
	//
	// obj.setListaProduto(listaProduto);
	// String jsonRetorno = "";
	// try {
	// jsonRetorno = mapper.writeValueAsString(obj);
	// } catch (JsonProcessingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return jsonRetorno;
	// }
	//
	//
	// @RequestMapping(value = "search", method = RequestMethod.GET)
	// @ResponseBody
	// public String search(HttpServletRequest request) {
	// System.out.println(request.getParameter("term"));
	// String search = produtoService.search(request.getParameter("term"));
	// System.out.println(search);
	// return search;
	// }
	//

	// @RequestMapping(value = "teste")
	// public String teste() {
	// String search = produtoService.search("Novo Produto");
	// System.out.println(search);
	// return search;
	// }

}

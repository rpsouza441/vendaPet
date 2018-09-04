package br.com.lojapet.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.lojapet.model.Carrinho;
import br.com.lojapet.model.CarrinhoItem;
import br.com.lojapet.model.Produto;
import br.com.lojapet.model.Venda;
import br.com.lojapet.persistence.service.CarteiraService;
import br.com.lojapet.persistence.service.ProdutoService;
import br.com.lojapet.persistence.service.VendaService;

@Controller
@RequestMapping(value = "/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarrinhoController {

	@Autowired
	protected VendaService vendaService;

	@Autowired
	protected CarteiraService carteiraService;

	@Autowired
	protected ProdutoService produtoService;

	@Autowired
	private Carrinho carrinho;

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

		ModelAndView modelAndView = redirecionaSeCarrinhoEstaVazio("redirect:/", "redirect:/produto/procurarProduto");

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

		venda.setDataEmissao(Calendar.getInstance());
		BigDecimal total = carrinho.getValorTotal();
		venda.setValorVenda(total);
		venda.setValorDesconto(BigDecimal.ZERO);
		venda.setValorFinal(total);
		venda.setParcela(1);
		modelAndView.addObject("venda", venda);

		return modelAndView;
	}

	@RequestMapping(value = "gerarOrcamento")
	public ModelAndView gerarOrcamento(Venda venda) {
		ModelAndView modelAndView = redirecionaSeCarrinhoEstaVazio("/venda/gerar_orcamento",
				"redirect:/produto/procurarProduto");

		venda.setDataEmissao(Calendar.getInstance());
		BigDecimal total = carrinho.getValorTotal();
		venda.setValorVenda(total);
		venda.setValorDesconto(BigDecimal.ZERO);
		venda.setValorFinal(total);
		venda.setParcela(1);
		modelAndView.addObject("venda", venda);

		return modelAndView;
	}

	@RequestMapping(value = "finalizarVenda")
	public ModelAndView finalizarVenda(Venda venda) {
		ModelAndView modelAndView = redirecionaSeCarrinhoEstaVazio("redirect:/", "redirect:/produto/procurarProduto");

		Venda vendaASerPersistida = new Venda();
		Collection<CarrinhoItem> itens = carrinho.getItens();
		List<Produto> produtos = itens.stream().map(CarrinhoItem::getProduto).collect(Collectors.toList());

		vendaASerPersistida.preencheVenda(venda, produtos, null, null);
		produtoService.removeQuantidade(carrinho.getUuidEQuantidade());
		vendaService.saveVenda(vendaASerPersistida);
		carrinho.limpaCarrinho();

		return modelAndView;
	}

	private CarrinhoItem createItem(UUID productId) {
		Produto produto = produtoService.getProdutoById(productId);
		CarrinhoItem item = new CarrinhoItem(produto);
		return item;
	}

	private ModelAndView redirecionaSeCarrinhoEstaVazio(String urlSePreenchido, String urlSeVazio) {

		ModelAndView modelAndView;
		if (!carrinho.getItens().isEmpty()) {
			modelAndView = new ModelAndView(urlSePreenchido);
		} else {
			return new ModelAndView(urlSeVazio);

		}
		return modelAndView;
	}

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

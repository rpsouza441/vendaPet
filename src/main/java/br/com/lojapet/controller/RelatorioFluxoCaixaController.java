package br.com.lojapet.controller;

import java.math.BigDecimal;
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
import br.com.lojapet.model.Compra;
import br.com.lojapet.model.Pagamento;
import br.com.lojapet.model.Venda;
import br.com.lojapet.persistence.service.CaixaService;
import br.com.lojapet.persistence.service.CompraService;
import br.com.lojapet.persistence.service.PagamentoService;
import br.com.lojapet.persistence.service.VendaService;

@Controller
public class RelatorioFluxoCaixaController {

	@Autowired
	protected CompraService compraService;

	@Autowired
	protected VendaService vendaService;

	@Autowired
	protected PagamentoService pagamentoService;

	@Autowired
	protected CaixaService caixaService;

	@RequestMapping(value = "/sistema/fluxoCaixa")
	public ModelAndView fluxoCaixa(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/relatorio/fluxo/fluxo_caixa");

		return modelAndView;
	}

	@RequestMapping(value = "/sistema/fluxoCaixa", method = RequestMethod.POST)
	public ModelAndView fluxoCaixaComSearch(
			@RequestParam(value = "startWith", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Calendar startWith,
			@RequestParam(value = "endWith", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Calendar endWith,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("/relatorio/fluxo/fluxo_caixa");
		List<Compra> listaCompra = new ArrayList<Compra>();
		List<Venda> listaVenda = new ArrayList<Venda>();
		String error = null;

		if (startWith == null) {
			startWith = Calendar.getInstance();
			startWith.add(Calendar.MONTH, -12);
			endWith = Calendar.getInstance();
		}
		listaCompra = compraService.findCompraBetweenOrderByEmissao(startWith, endWith);
		listaVenda = vendaService.findVendaBetweenOrderByEmissao(startWith, endWith);

		if (listaCompra.isEmpty() && listaVenda.isEmpty()) {
			error = "error.empty";
		}

		// Venda vendaDoMes = listaVenda.stream()
		// .filter(venda ->
		// startWith.get(Calendar.MONTH)==venda.getDataEmissao().get(Calendar.MONTH))
		// .findAny()
		// .orElse(new Venda());

		modelAndView.addObject("error", error);
		modelAndView.addObject("compras", listaCompra);
		modelAndView.addObject("vendas", listaVenda);
		modelAndView.addObject("totalVenda", totalVenda(listaVenda));
		modelAndView.addObject("totalCompra", totalCompra(listaCompra));
		return modelAndView;

	}

	@RequestMapping(value = "/relatorio/fluxoCaixa")
	public ModelAndView relatoriofluxoCaixa(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/relatorio/fluxo/relatorio_fluxo_caixa");

		return modelAndView;
	}

	@RequestMapping(value = "/relatorio/fluxoCaixa", method = RequestMethod.POST)
	public ModelAndView relatoriofluxoCaixaComSearch(@RequestParam(value = "simples", required = false) String simples,
			@RequestParam(value = "startWith", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Calendar startWith,
			@RequestParam(value = "endWith", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Calendar endWith,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("/relatorio/fluxo/relatorio_fluxo_caixa");
		List<Compra> listaCompra = new ArrayList<Compra>();
		List<Venda> listaVenda = new ArrayList<Venda>();
		String error = null;
		boolean showTableHead = false;

		if (startWith == null) {
			startWith = Calendar.getInstance();
			startWith.add(Calendar.MONTH, -12);
			endWith = Calendar.getInstance();
		}

		listaCompra = compraService.findCompraBetweenOrderByEmissao(startWith, endWith);
		listaVenda = vendaService.findVendaBetweenOrderByEmissao(startWith, endWith);

		if (listaCompra.isEmpty() && listaVenda.isEmpty()) {
			error = "error.empty";
		}

		// Venda vendaDoMes = listaVenda.stream()
		// .filter(venda ->
		// startWith.get(Calendar.MONTH)==venda.getDataEmissao().get(Calendar.MONTH))
		// .findAny()
		// .orElse(new Venda());

		modelAndView.addObject("inicio", startWith);
		modelAndView.addObject("fim", endWith);

		modelAndView.addObject("error", error);
		if (simples != null) {
			modelAndView.addObject("compras", listaCompra);
			modelAndView.addObject("vendas", listaVenda);
			showTableHead=true;
		}
		modelAndView.addObject("showTableHead", showTableHead);
		modelAndView.addObject("totalVenda", totalVenda(listaVenda));
		modelAndView.addObject("totalCompra", totalCompra(listaCompra));
		return modelAndView;

	}

	private BigDecimal totalCompra(List<Compra> listaCompra) {
		BigDecimal total = BigDecimal.ZERO;

		for (Compra compra : listaCompra) {
			total = total.add(compra.getTotal());
		}
		return total;
	}

	private BigDecimal totalVenda(List<Venda> listaVenda) {
		BigDecimal total = BigDecimal.ZERO;

		for (Venda venda : listaVenda) {
			total = total.add(venda.getTotal());
		}

		return total;
	}

}

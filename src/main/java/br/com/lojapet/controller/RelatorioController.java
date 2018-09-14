package br.com.lojapet.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import br.com.lojapet.model.Compra;
import br.com.lojapet.model.Pagamento;
import br.com.lojapet.model.Venda;
import br.com.lojapet.persistence.service.CompraService;
import br.com.lojapet.persistence.service.PagamentoService;
import br.com.lojapet.persistence.service.VendaService;

@Controller
public class RelatorioController {

	@Autowired
	protected CompraService compraService;

	@Autowired
	protected VendaService vendaService;

	@Autowired
	protected PagamentoService pagamentoService;
	
	@RequestMapping(value = "/sistema/fluxoCaixa")
	public ModelAndView fluxoCaixa(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/caixa/fluxo_caixa");

		return modelAndView;
	}

	@RequestMapping(value = "/sistema/fluxoCaixa", method = RequestMethod.POST)
	public ModelAndView fluxoCaixaComSearch(
			@RequestParam(value = "startWith", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Calendar startWith,
			@RequestParam(value = "endWith", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Calendar endWith,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("/caixa/fluxo_caixa");
		List<Compra> listaCompra = new ArrayList<Compra>();
		List<Venda> listaVenda = new ArrayList<Venda>();
		String error = null;

		if (startWith != null) {
			listaCompra = compraService.findCompraBetweenOrderByEmissao(startWith, endWith);
			listaVenda = vendaService.findVendaBetweenOrderByEmissao(startWith, endWith);
		}

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

	@RequestMapping(value = "/relatorio/conta/pagas")
	public ModelAndView relatorioContaPaga(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/relatorio/paga/lista");

		return modelAndView;
	}

	@RequestMapping(value = "/relatorio/conta/pagas", method = RequestMethod.POST)
	public ModelAndView relatorioContaPagaComSearch(
			@RequestParam(value = "startWith", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Calendar startWith,
			@RequestParam(value = "endWith", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Calendar endWith,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("/relatorio/paga/lista");
		List<Pagamento> pagamentos = new ArrayList<Pagamento>();
		String error = null;

		if (startWith != null) {
			pagamentos = pagamentoService.findContasPagasPorDataVencimentoPagasBetween(startWith, endWith);
		}

		if (pagamentos.isEmpty()) {
			error = "error.empty";
		}


		modelAndView.addObject("error", error);
		modelAndView.addObject("pagamentos", pagamentos);
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

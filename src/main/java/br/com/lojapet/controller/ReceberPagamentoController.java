package br.com.lojapet.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.lojapet.model.Caixa;
import br.com.lojapet.model.FormaDePagamento;
import br.com.lojapet.model.MovimentoDeCaixa;
import br.com.lojapet.model.Pagamento;
import br.com.lojapet.model.PagamentoEditForm;
import br.com.lojapet.model.PagamentoEfetuado;
import br.com.lojapet.model.User;
import br.com.lojapet.persistence.service.CaixaService;
import br.com.lojapet.persistence.service.MovimentoDeCaixaService;
import br.com.lojapet.persistence.service.PagamentoEfetuadoService;
import br.com.lojapet.persistence.service.PagamentoService;
import br.com.lojapet.persistence.service.UserService;

@Controller
@RequestMapping(value = "/conta")
public class ReceberPagamentoController {

	@Autowired
	protected PagamentoService pagamentoService;

	@Autowired
	protected PagamentoEfetuadoService pagamentoEfetuadoService;

	@Autowired
	private CaixaService caixaService;

	@Autowired
	private UserService userService;

	@Autowired
	private MovimentoDeCaixaService movimentoDeCaixaService;

	@RequestMapping(value = "receber", method = RequestMethod.GET)
	public ModelAndView listaRecebida(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/conta/recebida/lista");

		return modelAndView;
	}

	@RequestMapping(value = "receber", method = RequestMethod.POST)
	public ModelAndView listaRecebidaComSearch(
			@RequestParam(value = "startWith", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Calendar startWith,
			@RequestParam(value = "endWith", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Calendar endWith,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("/conta/recebida/lista");

		List<Pagamento> contasPagas;
		String error = null;

		if (startWith == null) {
			startWith = Calendar.getInstance();
			startWith.set(Calendar.DAY_OF_YEAR, -1);
			endWith = Calendar.getInstance();
		}
		contasPagas = pagamentoService.findContasRecebidasPorDataVencimentoBetween(startWith, endWith);

		if (contasPagas.isEmpty()) {
			error = "error.empty";
		}
		modelAndView.addObject("error", error);
		modelAndView.addObject("contas", contasPagas);
		return modelAndView;

	}

	@RequestMapping(value = "/editarContaRecebida/{uuid}")
	public ModelAndView editarContaRecebida(@PathVariable(value = "uuid") UUID uuid) {
		Pagamento pagamento = pagamentoService.getPagamentoById(uuid);
		PagamentoEditForm pagamentoEditForm = new PagamentoEditForm(pagamento);

		return formAReceber(pagamentoEditForm);

	}

	@RequestMapping(value = "/receber/cadastro")
	public ModelAndView formAReceber(PagamentoEditForm pagamentoEditForm) {
		ModelAndView modelAndView = new ModelAndView("/conta/recebida/edit");
		modelAndView.addObject("pagamentoEditForm", pagamentoEditForm);
		return modelAndView;
	}

	@RequestMapping(value = "gravarContaAReceber", method = RequestMethod.POST)
	public ModelAndView gravarContaAReceber(@Valid PagamentoEditForm pagamentoEditForm, BindingResult result,
			HttpServletRequest request) {

		if (request.getParameter("cancelar") != null) {
			pagamentoService.cancelaPagamento(pagamentoEditForm.getId());
			return new ModelAndView("redirect:/conta/receber");

		} else if (request.getParameter("baixa") != null) {
			PagamentoEfetuado pagamentoEfetuado = new PagamentoEfetuado();
			pagamentoEfetuado.montaParaBaixa(pagamentoEditForm);
			return formBaixaAReceber(pagamentoEfetuado);

		} else if (request.getParameter("estornar") != null) {
			Pagamento pagamentoById = pagamentoService.getPagamentoById(pagamentoEditForm.getId());
			pagamentoEfetuadoService.deletePagamentoEfetuado(pagamentoById.getListaPagamentosEfetuados());
			;
			pagamentoService.estornar(pagamentoEditForm.getId());
			return new ModelAndView("redirect:/conta/receber");

		}
		if (result.hasErrors()) {
			return formAReceber(pagamentoEditForm);
		}

		pagamentoService.updateCom(pagamentoEditForm);

		return new ModelAndView("redirect:/conta/pagar");

	}

	@RequestMapping(value = "/recebida/baixa")
	public ModelAndView formBaixaAReceber(PagamentoEfetuado pagamentoEfetuado) {
		ModelAndView modelAndView = new ModelAndView("/conta/recebida/baixa");
		modelAndView.addObject("pagamentoEfetuado", pagamentoEfetuado);
		modelAndView.addObject("formaDePagamento", FormaDePagamento.values());

		return modelAndView;
	}

	@RequestMapping(value = "baixaContaAReceber", method = RequestMethod.POST)
	public ModelAndView baixaContaAReceber(@Valid PagamentoEfetuado pagamentoEfetuado, BindingResult result) {

		if (pagamentoEfetuado.getPago().compareTo(pagamentoEfetuado.getSaldoDevedor()) > 0) {
			result.rejectValue("pago", "field.maiorQueTotal");

		}
		if (pagamentoEfetuado.getPago().compareTo(BigDecimal.ZERO) == 0) {
			result.rejectValue("pago", "field.zero");

		}

		if (result.hasErrors()) {
			return formBaixaAReceber(pagamentoEfetuado);
		}

		Pagamento pagamento = pagamentoService.getPagamentoById(pagamentoEfetuado.getPagamento().getId());

		pagamentoEfetuado.setPagamento(pagamento);

		PagamentoEfetuado savePagamentoEfetuadoWithReturn = pagamentoEfetuadoService
				.savePagamentoEfetuadoWithReturn(pagamentoEfetuado);

		User user = retornaUsuarioLogado();
		movimentoNoCaixaComPagamentoRecebido(savePagamentoEfetuadoWithReturn, user);

		pagamento.baixa(savePagamentoEfetuadoWithReturn);

		pagamentoService.updatePagamento(pagamento);

		return new ModelAndView("redirect:/conta/receber");

	}

	private void movimentoNoCaixaComPagamentoRecebido(PagamentoEfetuado savePagamentoEfetuadoWithReturn, User user) {
		MovimentoDeCaixa movimentoDeCaixa = new MovimentoDeCaixa();
		Caixa caixaAberto = caixaAberto();

		movimentoDeCaixa.geraUmMovimentoEntradaBaixa(savePagamentoEfetuadoWithReturn, caixaAberto, user);
		MovimentoDeCaixa saveMovimentoDeCaixaWithReturn = movimentoDeCaixaService
				.saveMovimentoDeCaixaWithReturn(movimentoDeCaixa);
		caixaAberto.addListaMovimentacao(saveMovimentoDeCaixaWithReturn);
		caixaService.saveCaixa(caixaAberto);

	}

	private Caixa caixaAberto() {
		return caixaService.getCaixaAberto();
	}

	private User retornaUsuarioLogado() {
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.getUserById(principal.getId());
	}
}

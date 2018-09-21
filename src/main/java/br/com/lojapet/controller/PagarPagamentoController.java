package br.com.lojapet.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.lojapet.model.FormaDePagamento;
import br.com.lojapet.model.Pagamento;
import br.com.lojapet.model.PagamentoEditForm;
import br.com.lojapet.model.PagamentoEfetuado;
import br.com.lojapet.persistence.service.PagamentoEfetuadoService;
import br.com.lojapet.persistence.service.PagamentoService;

@Controller
@RequestMapping(value = "/conta")
public class PagarPagamentoController {

	@Autowired
	protected PagamentoService pagamentoService;

	@Autowired
	protected PagamentoEfetuadoService pagamentoEfetuadoService;

	@RequestMapping(value = "pagar", method = RequestMethod.GET)
	public ModelAndView listaPaga(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/conta/paga/lista");
		return modelAndView;
	}

	@RequestMapping(value = "pagar", method = RequestMethod.POST)
	public ModelAndView listaPagaComSearch(
			@RequestParam(value = "startWith", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Calendar startWith,
			@RequestParam(value = "endWith", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Calendar endWith,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("/conta/paga/lista");

		List<Pagamento> contasPagas;
		String error = null;

		if (startWith == null) {
			startWith = Calendar.getInstance();
			startWith.set(Calendar.DAY_OF_YEAR, 1);
			endWith = Calendar.getInstance();
		}
		contasPagas = pagamentoService.findContasPagasPorDataVencimentoBetween(startWith, endWith);

		if (contasPagas.isEmpty()) {
			error = "error.empty";
		}
		modelAndView.addObject("error", error);
		modelAndView.addObject("contas", contasPagas);
		return modelAndView;

	}

	@RequestMapping(value = "/editarContaPaga/{uuid}")
	public ModelAndView editarContaPaga(@PathVariable(value = "uuid") UUID uuid) {
		Pagamento pagamento = pagamentoService.getPagamentoById(uuid);
		PagamentoEditForm pagamentoEditForm = new PagamentoEditForm(pagamento);

		return formAPagar(pagamentoEditForm);

	}

	@RequestMapping(value = "/pagar/cadastro")
	public ModelAndView formAPagar(PagamentoEditForm pagamentoEditForm) {
		ModelAndView modelAndView = new ModelAndView("/conta/paga/edit");
		modelAndView.addObject("pagamentoEditForm", pagamentoEditForm);
		return modelAndView;
	}

	@RequestMapping(value = "gravarContaAPagar", method = RequestMethod.POST)
	public ModelAndView gravarContaAPagar(@Valid PagamentoEditForm pagamentoEditForm, BindingResult result,
			HttpServletRequest request) {

		if (request.getParameter("cancelar") != null) {
			pagamentoService.cancelaPagamento(pagamentoEditForm.getId());
			return new ModelAndView("redirect:/conta/pagar");

		} else if (request.getParameter("baixa") != null) {
			PagamentoEfetuado pagamentoEfetuado = new PagamentoEfetuado();
			pagamentoEfetuado.montaParaBaixa(pagamentoEditForm);
			return formBaixaAPagar(pagamentoEfetuado);

		} else if (request.getParameter("estornar") != null) {
			Pagamento pagamentoById = pagamentoService.getPagamentoById(pagamentoEditForm.getId());
			pagamentoEfetuadoService.deletePagamentoEfetuado(pagamentoById.getListaPagamentosEfetuados());
			;
			pagamentoService.estornar(pagamentoEditForm.getId());
			return new ModelAndView("redirect:/conta/pagar");

		}
		if (result.hasErrors()) {
			return formAPagar(pagamentoEditForm);
		}

		pagamentoService.updateCom(pagamentoEditForm);

		return new ModelAndView("redirect:/conta/pagar");

	}

	@RequestMapping(value = "/pagar/baixa")
	public ModelAndView formBaixaAPagar(PagamentoEfetuado pagamentoEfetuado) {
		ModelAndView modelAndView = new ModelAndView("/conta/paga/baixa");
		modelAndView.addObject("pagamentoEfetuado", pagamentoEfetuado);
		modelAndView.addObject("formaDePagamento", FormaDePagamento.values());

		return modelAndView;
	}

	@RequestMapping(value = "baixaContaAPagar", method = RequestMethod.POST)
	public ModelAndView baixaContaAPagar(@Valid PagamentoEfetuado pagamentoEfetuado, BindingResult result) {

		if (pagamentoEfetuado.getPago().compareTo(pagamentoEfetuado.getSaldoDevedor()) > 0) {
			result.rejectValue("pago", "field.maiorQueTotal");

		}
		if (pagamentoEfetuado.getPago().compareTo(BigDecimal.ZERO) == 0) {
			result.rejectValue("pago", "field.zero");

		}

		if (result.hasErrors()) {
			return formBaixaAPagar(pagamentoEfetuado);
		}

		Pagamento pagamento = pagamentoService.getPagamentoById(pagamentoEfetuado.getPagamento().getId());

		pagamentoEfetuado.setPagamento(pagamento);

		PagamentoEfetuado savePagamentoEfetuadoWithReturn = pagamentoEfetuadoService
				.savePagamentoEfetuadoWithReturn(pagamentoEfetuado);

		pagamento.baixa(savePagamentoEfetuadoWithReturn);

		pagamentoService.updatePagamento(pagamento);

		return new ModelAndView("redirect:/conta/pagar");

	}

}

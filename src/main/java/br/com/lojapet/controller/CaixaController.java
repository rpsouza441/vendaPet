package br.com.lojapet.controller;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.lojapet.model.Caixa;
import br.com.lojapet.model.FormaDePagamento;
import br.com.lojapet.model.MovimentoDeCaixa;
import br.com.lojapet.model.OrigemMovimento;
import br.com.lojapet.model.TipoDeMovimentacao;
import br.com.lojapet.model.User;
import br.com.lojapet.persistence.service.CaixaService;
import br.com.lojapet.persistence.service.MovimentoDeCaixaService;
import br.com.lojapet.persistence.service.UserService;

@Controller
@RequestMapping(value = "/caixa")
public class CaixaController {

	@Autowired
	protected CaixaService caixaService;
	
	@Autowired
	private MovimentoDeCaixaService movimentoDeCaixaService;
	
	@Autowired
	private UserService userService;
	

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView lista(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/caixa/lista_caixa");
		Caixa caixa = caixaService.getCaixaAberto();
		List<MovimentoDeCaixa> listaMovimentacoes = caixa.getListaMovimentacoes();
		Calendar ultimoCaixaFechado = caixaService.getDateTimeUltimoCaixaFechado();
		modelAndView.addObject("ultimoCaixaFechado", ultimoCaixaFechado);
		modelAndView.addObject("listaMovimentacoes", listaMovimentacoes);
		return modelAndView;
	}

	@RequestMapping(value = "/")
	public ModelAndView home(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("index");
		
		return modelAndView;
	}

	@RequestMapping(value = "/entrada")
	public ModelAndView entrada(MovimentoDeCaixa movimentoDeCaixa) {
		ModelAndView modelAndView = new ModelAndView("/caixa/entrada_caixa");
		preencheSeVazio(movimentoDeCaixa);
		modelAndView.addObject("formaDePagamento", FormaDePagamento.values());
		modelAndView.addObject("movimentoDeCaixa", movimentoDeCaixa);

		return modelAndView;
	}


	@RequestMapping(value = "gravarEntrada", method = RequestMethod.POST)
	public ModelAndView gravarEntrada(@Valid MovimentoDeCaixa movimentoDeCaixa, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return entrada(movimentoDeCaixa);
		}
		preencheMovimento(movimentoDeCaixa, TipoDeMovimentacao.ENTRADA);

		persisteCaixaMovimento(caixaService.getCaixaAberto(), movimentoDeCaixa);

		return new ModelAndView("redirect:/caixa");

	}
	@RequestMapping(value = "/saida")
	public ModelAndView saida(MovimentoDeCaixa movimentoDeCaixa) {
		ModelAndView modelAndView = new ModelAndView("/caixa/saida_caixa");
		preencheSeVazio(movimentoDeCaixa);
		modelAndView.addObject("formaDePagamento", FormaDePagamento.values());
		modelAndView.addObject("movimentoDeCaixa", movimentoDeCaixa);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "gravarSaida", method = RequestMethod.POST)
	public ModelAndView gravarSaida(@Valid MovimentoDeCaixa movimentoDeCaixa, BindingResult result) {
		
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return entrada(movimentoDeCaixa);
		}
		
		preencheMovimento(movimentoDeCaixa, TipoDeMovimentacao.SAIDA);
		
		persisteCaixaMovimento(caixaService.getCaixaAberto(), movimentoDeCaixa);
		
		return new ModelAndView("redirect:/caixa");
		
	}
	@RequestMapping(value = "fecharCaixa", method = RequestMethod.GET)
	public ModelAndView fecharCaixa() {
		
		Caixa caixa = caixaService.getCaixaAberto();
		if(!caixa.getListaMovimentacoes().isEmpty()) {
			User logado = retornaUsuarioLogado();
			caixa.setAberto(false);
			caixa.setFechadoDataHora(Calendar.getInstance());
			caixa.setUser(logado);
			logado.addCaixa(caixa);
			
			userService.updateUser(logado);
			caixaService.updateCaixa(caixa);

		}
		
		
		return new ModelAndView("redirect:/caixa");
		
	}

	private void preencheMovimento(MovimentoDeCaixa movimentoDeCaixa, TipoDeMovimentacao tipo) {
		preencheSeVazio(movimentoDeCaixa);
		movimentoDeCaixa.setTipoDeMovimentacao(tipo);
		movimentoDeCaixa.setOrigemMovimento(OrigemMovimento.MANUAL);
		movimentoDeCaixa.setUser(retornaUsuarioLogado());
	}
	
	private void persisteCaixaMovimento(Caixa caixaAberto, MovimentoDeCaixa movimentoDeCaixa) {
		movimentoDeCaixa.setCaixa(caixaAberto);
		MovimentoDeCaixa movimentoDeCaixaPersistido = movimentoDeCaixaService.saveMovimentoDeCaixaWithReturn(movimentoDeCaixa);
 		caixaAberto.addMovimentacao(movimentoDeCaixaPersistido);
		caixaService.saveCaixa(caixaAberto);

	}
	
	private User retornaUsuarioLogado() {
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.getUserById(principal.getId());
	}
	
	private void preencheSeVazio(MovimentoDeCaixa movimentoDeCaixa) {
		if (movimentoDeCaixa.getDataHoraMovimento() == null) {
			movimentoDeCaixa.setDataHoraMovimento(Calendar.getInstance());

		}
	}


}

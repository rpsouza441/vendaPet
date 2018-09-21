package br.com.lojapet.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.lojapet.infra.FileSaver;
import br.com.lojapet.model.Grupo;
import br.com.lojapet.model.Produto;
import br.com.lojapet.persistence.service.GrupoService;
import br.com.lojapet.persistence.service.ProdutoService;

@RestController
@Controller
@RequestMapping(value = "/produto")
public class ProdutoController {

	@Autowired
	protected ProdutoService produtoService;
	@Autowired
	protected GrupoService grupoService;
	@Autowired
	private FileSaver fileSaver;

	private List<Grupo> grupos;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView lista() {
		ModelAndView modelAndView = new ModelAndView("/produto/lista_produto");
		return modelAndView;

	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView listaComSearch(@RequestParam(value = "search", required = false) String q,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("/produto/lista_produto");
		List<Produto> produtos;
		String error = null;
		
		if (q== null) {
			produtos = produtoService.getAllProdutos();
		} else {
			produtos = produtoService.searchSemRestrincao(q);
			
		}
		
		if (produtos.isEmpty() && q != null) {
			error = "error.empty";
		}
		modelAndView.addObject("error", error);
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
		
	}

	@RequestMapping(value = "procurarTodos", method = RequestMethod.POST)
	public ModelAndView procurarTodos(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/produto/lista_produto");
		return modelAndView;
	}

	@RequestMapping(value = "/cadastro")
	public ModelAndView form(Produto produto) {
		ModelAndView modelAndView = new ModelAndView("/produto/cadastro_produto");

		modelAndView.addObject("produto", produto);
		modelAndView.addObject("grupos", carregaGrupo());

		return modelAndView;
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ModelAndView cadastroProduto(MultipartFile endereco, @Valid Produto produto, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (produtoService.existeComNome(produto.getNome())) {
			result.rejectValue("nome", "field.produto.mustBe.Unique");
		}
		if (result.hasErrors()) {
			return form(produto);
		}
		if (!endereco.isEmpty()) {
			String path = fileSaver.write("arquivos", endereco);
			produto.setFoto(path);
		}

		produtoService.saveProduto(produto);

		return new ModelAndView("redirect:/produto/cadastro");

	}

	@RequestMapping(value = "/editarProduto/{uuid}")
	public ModelAndView editar(@PathVariable(value = "uuid") UUID uuid) {
		Produto produto = produtoService.getProdutoById(uuid);

		return form(produto);

	}

	@RequestMapping(value = "/excluirProduto/{uuid}")
	public ModelAndView excluirProduto(@PathVariable(value = "uuid") UUID uuid) {
		produtoService.deleteProduto(uuid);

		return new ModelAndView("redirect:/produto");

	}

	

	private List<Grupo> carregaGrupo() {
		grupos = new ArrayList<Grupo>();
		grupos = grupoService.getAllGrupos();
		return grupos;
	}

}

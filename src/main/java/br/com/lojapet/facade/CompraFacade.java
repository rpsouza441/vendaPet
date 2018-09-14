package br.com.lojapet.facade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.lojapet.model.Fornecedor;
import br.com.lojapet.model.Produto;
import br.com.lojapet.persistence.service.CompraService;
import br.com.lojapet.persistence.service.FornecedorService;
import br.com.lojapet.persistence.service.ProdutoService;

public class CompraFacade {

	private XmlFacade xmlFacade;

	private ProdutoService produtoService;

	protected CompraService compraService;

	protected FornecedorService fornecedorService;

	private List<Produto> listaProduto = new ArrayList<>();

	private Fornecedor fornecedor;

	public CompraFacade(XmlFacade xmlFacade, ProdutoService produtoService, CompraService compraService,
			FornecedorService fornecedorService) {
		this.xmlFacade = xmlFacade;
		this.produtoService = produtoService;
		this.compraService = compraService;
		this.fornecedorService = fornecedorService;
	}
	
	public XmlFacade persisteProdutoFornecedor() {
		persisteFornecedorNfXml();
		xmlFacade.atrelaFornecedorAosProdutos(fornecedor);
		persisteProdutoNfXml();
		
		xmlFacade.setFornecedor(fornecedor);
		xmlFacade.setProdutos(listaProduto);
		
		
		return xmlFacade;
	}

	private void persisteProdutoNfXml() {

		for (Produto produto : xmlFacade.getProdutos()) {
			if (!produtoService.produtoExisteCom(produto.getNome())) {
				
				listaProduto.add(produtoService.saveProdutoWithReturn(produto));
			} else {
				Produto produtoByNome = produtoService.getProdutoByNome(produto.getNome());
				produtoByNome.adicionaQuantidade(produto.getQuantidade());
				produtoByNome.setValorCusto(produto.getValorCusto());
				listaProduto.add(produtoService.updateProduto(produtoByNome));
			}

		}

	}
	
	private void persisteFornecedorNfXml() {
		
			if (!fornecedorService.fornecedorExisteCom(xmlFacade.getFornecedor().getNome())) {
				 fornecedor = fornecedorService.saveFornecedorWithReturn(xmlFacade.getFornecedor());
			} else {
				fornecedor=fornecedorService.getFornecedorByNome(xmlFacade.getFornecedor().getNome());
			
		}
		
	}
	
	
	
	
	

}

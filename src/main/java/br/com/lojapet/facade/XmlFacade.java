package br.com.lojapet.facade;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import br.com.lojapet.model.Compra;
import br.com.lojapet.model.Fornecedor;
import br.com.lojapet.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class XmlFacade {
	List<Produto> produtos = new ArrayList<>();
	Fornecedor fornecedor = new Fornecedor();
	Compra compra = new Compra();

	public void atrelaFornecedorAosProdutos(Fornecedor fornecedorPersistido) {
		for (Produto produto : produtos) {
			produto.addFornecedor(fornecedorPersistido);

		}
	}
	
	public void geraTotalCompra() {
		for (Produto produto : produtos) {
			compra.setTotal(produto.getValorCusto().add(compra.getTotal()));
			System.out.println(compra.getTotal());
		}
		compra.setSubtotal(compra.getTotal());
	}

}

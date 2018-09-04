package br.com.lojapet.model;

import java.math.BigDecimal;
import java.util.UUID;

public class CarrinhoItem {

	private Produto produto;

	private UUID productId;

	public static CarrinhoItem zeroed() {
		Produto produto = new Produto();
		return new CarrinhoItem(produto);
	}

	public CarrinhoItem(Produto produto) {
		this.produto = produto;
		this.productId = produto.getId();
	}

	public Produto getProduto() {
		return produto;
	}

	public BigDecimal getPreco() {
		return produto.getValorVenda();
	}

	public BigDecimal getTotal(Integer quantity) {
		BigDecimal multiply = getPreco().multiply(new BigDecimal(quantity));
		multiply = multiply.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return multiply;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrinhoItem other = (CarrinhoItem) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

}

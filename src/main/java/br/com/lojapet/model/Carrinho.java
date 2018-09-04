package br.com.lojapet.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class Carrinho implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();

	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

	public void limpaCarrinho() {
		itens.clear();
	}

	// Método Novo
	public void add(CarrinhoItem item, Integer quantidade) {
		itens.put(item, getQuantidade(item) + quantidade);
	}

	public int getQuantidade(CarrinhoItem item) {
		if (!itens.containsKey(item)) {
			itens.put(item, 0);
		}
		return itens.get(item);
	}

	public int getQuantidade() {
		return itens.values().stream().reduce(0, (proximo, acumulador) -> (proximo + acumulador));
	}

	public void updateQuantidade(CarrinhoItem item, int quantidade) {

		itens.computeIfPresent(item, (k, v) -> v = quantidade);

	}

	public Collection<CarrinhoItem> getItens() {
		return itens.keySet();
	}

	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getTotal(getQuantidade(item));
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}

	public BigDecimal getValorTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}

	public void remover(CarrinhoItem item) {
		itens.remove(item);

	}

	public Map<UUID, Integer> getUuidEQuantidade() {
		Map<UUID, Integer> listaDeUuidEQuantidade = new LinkedHashMap<UUID, Integer>();

		itens.forEach((k, v) -> listaDeUuidEQuantidade.put(k.getProduto().getId(), v));
		return listaDeUuidEQuantidade;

	}

}

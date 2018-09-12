package br.com.lojapet.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
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

	private Map<CarrinhoItem, Integer> itensCarrinho = new LinkedHashMap<CarrinhoItem, Integer>();
	
	private Calendar dataEmissao;

	public Calendar getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Calendar dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public void add(CarrinhoItem item) {
		itensCarrinho.put(item, getQuantidade(item) + 1);
	}

	public void limpaCarrinho() {
		itensCarrinho.clear();
	}

	// Método Novo
	public void add(CarrinhoItem item, Integer quantidade) {
		itensCarrinho.put(item, getQuantidade(item) + quantidade);
	}

	public int getQuantidade(CarrinhoItem item) {
		if (!itensCarrinho.containsKey(item)) {
			itensCarrinho.put(item, 0);
		}
		return itensCarrinho.get(item);
	}

	public int getQuantidade() {
		return itensCarrinho.values().stream().reduce(0, (proximo, acumulador) -> (proximo + acumulador));
	}

	public void updateQuantidade(CarrinhoItem item, int quantidade) {

		itensCarrinho.computeIfPresent(item, (k, v) -> v = quantidade);

	}

	public Collection<CarrinhoItem> getItens() {
		return itensCarrinho.keySet();
	}

	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getTotal(getQuantidade(item));
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem item : itensCarrinho.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}

	public BigDecimal getValorTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem item : itensCarrinho.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}

	public void remover(CarrinhoItem item) {
		itensCarrinho.remove(item);

	}

	public Map<UUID, Integer> getUuidEQuantidade() {
		Map<UUID, Integer> listaDeUuidEQuantidade = new LinkedHashMap<UUID, Integer>();

		itensCarrinho.forEach((k, v) -> listaDeUuidEQuantidade.put(k.getProduto().getId(), v));
		return listaDeUuidEQuantidade;

	}

}

package br.com.lojapet.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * 
 * VENDA REALIZADA OU ENTRADA DE DINHEIRO OU CONTA A RECEBER
 * 
 */

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private Calendar dataEmissao;

	@NumberFormat(style = Style.NUMBER)
	private BigDecimal subtotal;

	@NumberFormat(style = Style.NUMBER)
	private BigDecimal desconto = BigDecimal.ZERO;

	@NumberFormat(style = Style.NUMBER)
	private BigDecimal total;

	@Transient
	private int parcelas;

	@ManyToOne
	@JoinColumn(name = "cliente_venda_id", foreignKey = @ForeignKey(name = "venda_cliente_fk"))
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "usuario_venda_id", foreignKey = @ForeignKey(name = "venda_usuario_fk"))
	private User user;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "venda_pagamento_id", foreignKey = @ForeignKey(name = "pagamento_venda_fk"))
	private List<Pagamento> contaAReceber = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "venda_produto", joinColumns = @JoinColumn(name = "venda_produto_id"), inverseJoinColumns = @JoinColumn(name = "produto_venda_id"), indexes = {
			@Index(name = "venda_produto_fk", columnList = "venda_produto_id"),
			@Index(name = "produto_venda_fk", columnList = "produto_venda_id") })
	private List<Produto> listaProduto = new ArrayList<>();

	public String getDadosCliente() {
		String saida = "";
		if (cliente != null) {
			saida = "Venda para cliente: " + cliente.getNomeCompleto();
		}
		return saida;
	}

	public void montaVenda(BigDecimal valorTotal) {
		if (this.dataEmissao == null) {
			this.dataEmissao = Calendar.getInstance();
			this.subtotal = valorTotal;
			this.total = valorTotal;
			contaAReceber = Arrays.asList(Pagamento.builder().total(valorTotal).dataVencimento(dataEmissao)
					.dataPagamento(dataEmissao).contaRecebida(this).build());
		}

	}

	public void gerarParcelas() {
		List<Pagamento> novoParcelamento = new ArrayList<>();
		BigDecimal valorParcelado = this.subtotal.divide(new BigDecimal(parcelas), 2, RoundingMode.HALF_UP);
		Calendar dataVecimentoParcelamento;
		for (int i = 1; i <= parcelas; i++) {
			dataVecimentoParcelamento = (Calendar) this.dataEmissao.clone();
			dataVecimentoParcelamento.add(Calendar.MONTH, i);

			novoParcelamento.add(Pagamento.builder().total(valorParcelado).pago(BigDecimal.ZERO).aPagar(valorParcelado)
					.dataVencimento(dataVecimentoParcelamento).estaQuitado(StatusConta.NAOQUITADO).contaRecebida(this)
					.build());

		}
		this.contaAReceber = novoParcelamento;

	}

	public void preparaVendaParaPersistir(List<Produto> produtos, User logado, Cliente clienteTemp) {
		this.cliente = ((clienteTemp == null) ? null : clienteTemp);
		this.listaProduto = produtos;
		this.user = logado;
		this.total = this.subtotal.subtract(this.desconto);
		sePrimeiroVencimentoIgualAEmissaoEstaPago();

	}

	private void sePrimeiroVencimentoIgualAEmissaoEstaPago() {
		if (this.contaAReceber.get(0).getDataVencimento() == this.dataEmissao) {
			this.contaAReceber.get(0).setDataPagamento(this.dataEmissao);
			this.contaAReceber.get(0).setPago(this.contaAReceber.get(0).getTotal());
			this.contaAReceber.get(0).setAPagar(BigDecimal.ZERO);
			this.contaAReceber.get(0).setEstaQuitado(StatusConta.QUITADO);

		}
	}

}

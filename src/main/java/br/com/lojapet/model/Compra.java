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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * 
 * COMPRA REALIZADA OU SAIDA DE DINHEIRO OU CONTA A PAGAR
 * 
 */

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Compra implements Serializable {

	/**
	 * 
	 */
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

	@NotNull
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal subtotal;

	@NumberFormat(style = Style.NUMBER)
	private BigDecimal desconto;

	@NotNull
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal total;

	private String observacao="";

	@Transient
	private int parcelas;

	@Transient
	private String enderecoPath;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "fornecedor_compra_id", foreignKey = @ForeignKey(name = "compra_fornecedor_fk"))
	private Fornecedor fornecedor;

	@ManyToOne
	@JoinColumn(name = "usuario_compra_id", foreignKey = @ForeignKey(name = "compra_usuario_fk"))
	private User user;

	@ManyToMany
	@JoinTable(name = "compra_produto", joinColumns = @JoinColumn(name = "compra_produto_id"), inverseJoinColumns = @JoinColumn(name = "produto_compra_id"), indexes = {
			@Index(name = "compra_produto_fk", columnList = "compra_produto_id"),
			@Index(name = "produto_compra_fk", columnList = "produto_compra_id") })
	private List<Produto> listaProduto = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "compra_pagamento_id", foreignKey = @ForeignKey(name = "pagamento_compra_fk"))
	private List<Pagamento> contaAPagar = new ArrayList<>();

	public void montaCompra() {
		if (this.dataEmissao == null) {
			this.subtotal = this.total;
			contaAPagar = Arrays
					.asList(Pagamento.builder()
							.total(total)
							.dataEmissao(this.dataEmissao)
							.dataVencimento(this.dataEmissao)
							.dataPagamento(this.dataEmissao)
							.contaPaga(this)
							.build());
		}

	}

	public void gerarParcelas() {
		List<Pagamento> novoParcelamento = new ArrayList<>();
		BigDecimal valorParcelado = BigDecimal.ZERO;
		if (this.total != null) {
			valorParcelado = this.subtotal.divide(new BigDecimal(parcelas), 2, RoundingMode.HALF_UP);
		}
		Calendar dataVecimentoParcelamento;
		for (int i = 1; i <= parcelas; i++) {
			dataVecimentoParcelamento = (Calendar) this.dataEmissao.clone();
			dataVecimentoParcelamento.add(Calendar.MONTH, i);

			novoParcelamento.add(Pagamento.builder().total(valorParcelado).pago(BigDecimal.ZERO).aPagar(valorParcelado)
					.dataEmissao(this.dataEmissao).dataVencimento(dataVecimentoParcelamento)
					.estaQuitado(StatusConta.NAOQUITADO).contaPaga(this).build());

		}
		this.contaAPagar = novoParcelamento;

	}

	public void montaCompra(List<Produto> listaProdutosCompra) {
		this.listaProduto.addAll(listaProdutosCompra);
		if (this.dataEmissao == null) {
			this.subtotal = this.total;
			zeraQuantidadeParaTelaDeCompra();
			this.dataEmissao = Calendar.getInstance();
			contaAPagar = Arrays.asList(Pagamento.builder().total(total).dataEmissao(this.dataEmissao)
					.dataVencimento(this.dataEmissao).dataPagamento(this.dataEmissao).contaPaga(this).build());
		}

	}

	public void preparaCompraParaPersistir(User logado) {
		this.user = logado;
		montaPagamentos();

	}

	private void montaPagamentos() {
		for (Pagamento pagamento : contaAPagar) {
//			System.out.println(pagamento.);
			if (pagamento.vencimentoMaiorQueEmissao(this.dataEmissao)) {
				pagamento.setDataPagamento(this.dataEmissao);
				pagamento.setDataEmissao(this.dataEmissao);
				pagamento.setPago(pagamento.getTotal());
				pagamento.setAPagar(BigDecimal.ZERO);
				pagamento.setEstaQuitado(StatusConta.QUITADO);
				pagamento.setObservacao(descricaoProdutos());
				pagamento.pagamentoUmaVez();
				pagamento.geraPagamentoEfetuado();
//				if(cliente==null) {
//					pagamento.geraPagamentoEfetuado("");
//				}else {
//					pagamento.geraPagamentoEfetuado("Cliente "+cliente.getNomeCompleto());
//					
//				}
			} else {
				pagamento.setAPagar(pagamento.getTotal());
				pagamento.setDataEmissao(this.dataEmissao);
				pagamento.setPago(BigDecimal.ZERO);
				pagamento.setEstaQuitado(StatusConta.NAOQUITADO);
				pagamento.setObservacao(descricaoProdutos());

			}

		}
	}

	private boolean vencimentoMaiorQueEmissao(Calendar vencimento, Calendar emissao) {
		Calendar vencimentoClone = (Calendar) vencimento.clone();
		Calendar emissaoClone = (Calendar) emissao.clone();
		vencimentoClone.set(Calendar.HOUR_OF_DAY, 0);
		vencimentoClone.set(Calendar.MINUTE, 0);
		vencimentoClone.set(Calendar.SECOND, 0);
		vencimentoClone.set(Calendar.MILLISECOND, 0);
		emissaoClone.set(Calendar.HOUR_OF_DAY, 0);
		emissaoClone.set(Calendar.MINUTE, 0);
		emissaoClone.set(Calendar.SECOND, 0);
		emissaoClone.set(Calendar.MILLISECOND, 0);

		return vencimentoClone.equals(emissaoClone);
	}

	private String descricaoProdutos() {
		String descricao = "";
		for (Produto p : listaProduto) {
			descricao = p.getQuantidade() + " x " + p.getNome();
		}
		return descricao;
	}

	private void zeraQuantidadeParaTelaDeCompra() {
		for (Produto p : listaProduto) {
			p.setQuantidade(1);
			this.observacao+=p.getQuantidade() + " x " + p.getNome()+" ";

		}
	}

	public void atualizaSubtotal() {
		BigDecimal somatorio = BigDecimal.ZERO;
		this.observacao="";
		for (Produto p : listaProduto) {
			somatorio = somatorio.add(new BigDecimal(p.getQuantidade()).multiply(p.getValorCusto()));
			this.observacao+=p.getQuantidade() + " x " + p.getNome()+" ";

		}
		this.subtotal = BigDecimal.ZERO;
		this.subtotal = somatorio;
	}

	public void geraObservacao(List<Produto> list) {
		for (Produto p : list) {
			this.observacao+=p.getQuantidade() + " x " + p.getNome()+" ";
				
		}
	}
}

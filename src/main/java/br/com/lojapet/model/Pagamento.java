package br.com.lojapet.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.engine.internal.Cascade;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pagamento implements Serializable {

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
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal total;

	@NumberFormat(style = Style.NUMBER)
	private BigDecimal pago;

	@NumberFormat(style = Style.NUMBER)
	private BigDecimal aPagar;

	private String observacao;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private Calendar dataEmissao;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dataVencimento;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dataPagamento;

	private StatusConta estaQuitado;
	
	@Transient
	private FormaDePagamento formaDePagamento;

	@ManyToOne
	@JoinColumn(name = "venda_pagamento_id", foreignKey = @ForeignKey(name = "pagamento_venda_fk"))
	private Venda contaRecebida;

	@ManyToOne
	@JoinColumn(name = "compra_pagamento_id", foreignKey = @ForeignKey(name = "pagamento_compra_fk"))
	private Compra contaPaga;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pagamento_efetuado_id", foreignKey = @ForeignKey(name = "efetuado_pagamento_fk"))
	private List<PagamentoEfetuado> listaPagamentosEfetuados = new ArrayList<>();

	public void updateCom(PagamentoEditForm pagamentoEditForm) {
		this.total = (pagamentoEditForm.getTotal());
		this.pago = (pagamentoEditForm.getPago());
		this.aPagar = (pagamentoEditForm.getAPagar());
		this.observacao = (pagamentoEditForm.getObservacao());
		this.dataVencimento = (pagamentoEditForm.getDataVencimento());

	}

	public void baixa(PagamentoEfetuado pagamentoEfetuado) {
		if (this.aPagar.compareTo(pagamentoEfetuado.getPago()) == 0) {
			estaQuitado = StatusConta.QUITADO;
			aPagar = BigDecimal.ZERO;
			pago = pagamentoEfetuado.getPago();
			listaPagamentosEfetuados.add(pagamentoEfetuado);
		} else {
			aPagar = total.subtract(pagamentoEfetuado.getPago());
			pago = pagamentoEfetuado.getPago();
			listaPagamentosEfetuados.add(pagamentoEfetuado);
		}
	}

	public void pagamentoUmaVez() {
		PagamentoEfetuado efetuado = new PagamentoEfetuado();
		efetuado.setPago(this.pago);
		efetuado.setDataPagamento(this.dataEmissao);
		efetuado.setFormaDePagamento(this.formaDePagamento);
		efetuado.setPagamento(this);
		listaPagamentosEfetuados.add(efetuado);
		
	}
	
	

}

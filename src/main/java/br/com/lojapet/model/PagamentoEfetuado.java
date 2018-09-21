package br.com.lojapet.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PagamentoEfetuado implements Serializable {

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
	private BigDecimal pago;
	
	@Transient
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal saldoDevedor;

	private String observacao;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dataPagamento;

	private FormaDePagamento formaDePagamento;
	
	@ManyToOne
	@JoinColumn(name = "pagamento_efetuado_id", foreignKey = @ForeignKey(name = "efetuado_pagamento_fk"))
	private Pagamento pagamento;

	public void montaParaBaixa( PagamentoEditForm pagamentoEditForm) {
		
		this.pago=pagamentoEditForm.getAPagar();
		this.saldoDevedor=pagamentoEditForm.getAPagar();
		this.dataPagamento=Calendar.getInstance();
		this.pagamento = new Pagamento();
		this.pagamento.setId(pagamentoEditForm.getId());
		
	}

	
	

}

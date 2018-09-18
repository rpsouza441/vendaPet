package br.com.lojapet.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoEditForm implements Serializable{

	private List<PagamentoEfetuado> listaPagamentosEfetuados;

	public PagamentoEditForm(Pagamento pagamento) {
		this.id=(pagamento.getId());
		this.total=(pagamento.getTotal());
		this.pago=(pagamento.getPago());
		this.aPagar=(pagamento.getAPagar());
		this.observacao=(pagamento.getObservacao());
		this.dataVencimento=(pagamento.getDataVencimento());
		this.listaPagamentosEfetuados =pagamento.getListaPagamentosEfetuados();
		
		for (PagamentoEfetuado pagamentoEfetuado : listaPagamentosEfetuados) {
			System.out.println("pagamento "+ pagamentoEfetuado.getPago());
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dataVencimento;



}

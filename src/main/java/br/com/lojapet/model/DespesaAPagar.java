package br.com.lojapet.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DespesaAPagar {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@NotEmpty
	private String nome;
	
	@NotNull
	@DateTimeFormat
	private Calendar dataVencimento;
	
	@DateTimeFormat
	private Calendar dataPagamento;
	
	private boolean estaPago = Boolean.FALSE;
	
	@Transient
	private Periodicidade periodicidade;
	
	@Transient
	private int quantidadeParcelas;
	
	@Builder.Default
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal valor = BigDecimal.ZERO;

	@ManyToOne
	@JoinColumn(name = "carteira_despesa_id", foreignKey = @ForeignKey(name = "despesasapagar_carteira_fk"))
	private Carteira carteira;

	
	

}

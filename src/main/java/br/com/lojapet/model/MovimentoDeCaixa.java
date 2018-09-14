package br.com.lojapet.model;

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
public class MovimentoDeCaixa {
	
	@Id
	@Column(columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private Calendar dataHoraMovimento;
	
	@NotNull
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal valor ;
	
	private String observacao;
	
	private FormaDePagamento formaDePagamento;

	private OrigemMovimento origemMovimento;
	
	private TipoDeMovimentacao tipoDeMovimentacao; 
	
	@ManyToOne
	@JoinColumn(name = "usuario_movimento_id", foreignKey = @ForeignKey(name = "movimento_usuario_fk"))
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "caixa_movimento_id", foreignKey = @ForeignKey(name = "movimento_caixa_fk"))
	private Caixa caixa;
	

}

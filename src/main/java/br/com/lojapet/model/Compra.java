package br.com.lojapet.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.FutureOrPresent;
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
public class Compra {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@NotNull
	@FutureOrPresent
	@DateTimeFormat
	private Calendar dataEmissao;
	
	@Transient
	private String enderecoPath;
	
	@NotNull
	@Builder.Default
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal valorVenda = BigDecimal.ZERO;
	
	@Builder.Default
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal Desconto = BigDecimal.ZERO;
	
	@NotNull
	@Builder.Default
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal valorFinal = BigDecimal.ZERO;

	@ManyToOne
	@JoinColumn(name = "fornecedor_compra_id", foreignKey = @ForeignKey(name = "compra_fornecedor_fk"))
	private Fornecedor fornecedor;

	@ManyToOne
	@JoinColumn(name = "carteira_compra_id", foreignKey = @ForeignKey(name = "compra_carteira_fk"))
	private Carteira carteira;

	@OneToMany
//	(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "compra_produto_id", foreignKey = @ForeignKey(name = "compra_produto_fk"))
	private List<Produto> produto;

}

//package br.com.lojapet.model;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.ForeignKey;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.validation.constraints.NotEmpty;
//
//import org.hibernate.annotations.GenericGenerator;
//import org.springframework.format.annotation.NumberFormat;
//import org.springframework.format.annotation.NumberFormat.Style;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Builder
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class Carteira {
//
//	@Id
//	@Column(columnDefinition = "BINARY(16)")
//	@GeneratedValue(generator = "UUID")
//	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//	private UUID id;
//
//	@NotEmpty
//	private String nome;
//
//	@Builder.Default
//	@NumberFormat(style = Style.NUMBER)
//	private BigDecimal saldo = BigDecimal.ZERO;
//
//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name = "carteira_despesa_id", foreignKey = @ForeignKey(name = "carteira_despesa_fk"))
//	private List<DespesaAPagar> despesas;
//
//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name = "carteira_compra_id", foreignKey = @ForeignKey(name = "carteira_compra_fk"))
//	private List<Compra> compras;
//
//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name = "carteira_venda_id", foreignKey = @ForeignKey(name = "carteira_venda_fk"))
//	private List<Venda> vendas = new ArrayList<Venda>();
//
//	public void addVenda(Venda vendaASerPersistida) {
//		vendas.add(vendaASerPersistida);
//	}
//
//}

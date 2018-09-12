//package br.com.lojapet.model;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.UUID;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.ForeignKey;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import org.hibernate.annotations.GenericGenerator;
//import org.springframework.format.annotation.DateTimeFormat;
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
//public class Parcela {
//
//	@Id
//	@Column(columnDefinition = "BINARY(16)")
//	@GeneratedValue(generator = "UUID")
//	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//	private UUID id;
//
//	private int parcela;
//
//	private int parcelaTotal;
//
//	private StatusContaAReceber statusContaAReceber;
//
//	private FormaDePagamento formaDePagamento;
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
//	private Calendar dataEmissao;
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
//	private Calendar dataVencimento;
//
//	@NumberFormat(style = Style.NUMBER)
//	private BigDecimal total;
//	
//	@NumberFormat(style = Style.NUMBER)
//	private BigDecimal recebido;
//	
//	@NumberFormat(style = Style.NUMBER)
//	private BigDecimal aReceber;
//	
//	@OneToMany
//	@JoinColumn(name = "parcela_venda_id", foreignKey = @ForeignKey(name = "venda_parcela_fk"))
//	private List<Venda> listaDeContaRecebida = new ArrayList<>();
//
//	@ManyToOne
//	@JoinColumn(name = "usuario_parcela_id", foreignKey = @ForeignKey(name = "parcela_usuario_fk"))
//	private User user;
//
//}

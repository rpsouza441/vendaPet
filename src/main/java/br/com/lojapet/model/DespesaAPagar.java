//package br.com.lojapet.model;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Calendar;
//import java.util.UUID;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.ForeignKey;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.persistence.Transient;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
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
//
//@Builder
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class DespesaAPagar implements Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
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
//	@NotNull
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
//	@Temporal(TemporalType.DATE)
//	private Calendar dataVencimento;
//
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
//	@Temporal(TemporalType.DATE)
//	private Calendar dataPagamento;
//
//	private StatusContaAPagar statusContaAPagar;
//
//	@Transient
//	private Periodicidade periodicidade;
//
//	@Transient
//	private int quantidadeParcelas;
//
//	@Builder.Default
//	@NumberFormat(style = Style.NUMBER)
//	private BigDecimal valor = BigDecimal.ZERO;
//
//	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		DespesaAPagar other = (DespesaAPagar) obj;
//		if (id == null) {
//			if (other.getId()!= null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		return true;
//	}
//
//}

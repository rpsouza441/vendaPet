//package br.com.lojapet.model;
//
//import java.math.BigDecimal;
//import java.util.Calendar;
//import java.util.UUID;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
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
////@Entity
//public class ContasAReceber {
//
//	
////	@Id
////	@Column(columnDefinition = "BINARY(16)")
////	@GeneratedValue(generator = "UUID")
////	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
////	private UUID id;
//	
////	@DateTimeFormat
//	private Calendar dataEmissao;
//	
////	@DateTimeFormat
//	private Calendar dataVencimento;
//	
////	@Builder.Default
////	@NumberFormat(style = Style.NUMBER)
//	private BigDecimal total = BigDecimal.ZERO;
//	
////	@Builder.Default
////	@NumberFormat(style = Style.NUMBER)
//	private BigDecimal recebido = BigDecimal.ZERO;
//	
//	
//	
//}

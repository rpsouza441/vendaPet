package br.com.lojapet.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class Venda {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@NotNull
	@DateTimeFormat
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Calendar dataEmissao;

	@NotNull
	@Builder.Default
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal valorVenda = BigDecimal.ZERO;

	@NotNull
	@Builder.Default
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal valorDesconto = BigDecimal.ZERO;

	@NotNull
	@Builder.Default
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal valorFinal = BigDecimal.ZERO;

	@ManyToMany
	@JoinTable(name = "venda_produto", joinColumns = @JoinColumn(name = "venda_produto_id"), inverseJoinColumns = @JoinColumn(name = "produto_venda_id"), indexes = {
			@Index(name = "venda_produto_fk", columnList = "venda_produto_id"),
			@Index(name = "produto_venda_fk", columnList = "produto_venda_id") })
	private List<Produto> listaProduto = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "carteira_venda_id", foreignKey = @ForeignKey(name = "carteira_venda_fk"))
	private Carteira carteira;

	@ManyToOne
	@JoinColumn(name = "cliente_venda_id", foreignKey = @ForeignKey(name = "venda_cliente_fk"))
	private Cliente cliente;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "venda_parcela_id", foreignKey = @ForeignKey(name = "venda_parcela_fk"))
	private List<Parcela> parcelamento = new ArrayList<>();

	@Transient
	private int parcela;

	public void preencheVenda(Venda temp, List<Produto> listaProdutoTemp, Carteira cateiraTemp, Cliente clienteTemp) {
		this.dataEmissao = temp.getDataEmissao();
		this.valorVenda = temp.getValorVenda();
		this.valorDesconto = temp.getValorDesconto();
		this.valorFinal = temp.getValorFinal();
		this.listaProduto = listaProdutoTemp;
		this.carteira = cateiraTemp;
		this.cliente = clienteTemp;
		this.parcela = temp.getParcela();
		montaParcelamento();

	}

	private void montaParcelamento() {
		if (parcela > 1) {
			BigDecimal valorDasParcelas = this.valorFinal.divide(new BigDecimal(parcela), 2, RoundingMode.HALF_UP);
			for (int i = 1; i <= parcela; i++) {
				Parcela parcelaTemp = new Parcela();
				parcelaTemp.setValor(valorDasParcelas);
				parcelaTemp.setParcela(i);
				parcelaTemp.setParcelaTotal(parcela);
				parcelaTemp.setEstaPago(false);
				parcelaTemp.setDataVencimento(dataEmissao);
				parcelaTemp.getDataVencimento().add(Calendar.MONTH, i);
				parcelamento.add(parcelaTemp);

			}
		}

	}

}

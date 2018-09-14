package br.com.lojapet.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
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
public class Produto {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@NotEmpty
	private String nome;

	private String descricao;

	private long codBarras;

	private String unidade;

	private int minEstoque;

	private int maxEstoque;

	@NumberFormat(style = Style.NUMBER)
	private BigDecimal valorCusto ;

	@NotNull
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal valorVenda ;

	@NumberFormat(style = Style.NUMBER)
	private BigDecimal margemLucro ;

	private String fabricante;

	private String foto;

	private String observacoes;
	
	private boolean estaAtivo = Boolean.TRUE;

	@ManyToOne
	@JoinColumn(name = "grupo_produto_id")
	private Grupo grupo;

	private long quantidade;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "produto_fornecedor", joinColumns = @JoinColumn(name = "produto_fornecedor_id"), inverseJoinColumns = @JoinColumn(name = "fornecedor_produto_id"), indexes = {
			@Index(name = "produto_fornecedor_fk", columnList = "produto_fornecedor_id"),
			@Index(name = "fornecedor_produto_fk", columnList = "fornecedor_produto_id") })
	private List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

	@ManyToMany(mappedBy = "listaProduto", fetch = FetchType.LAZY)
	private List<Compra> listaCompra;
	
	@ManyToMany(mappedBy = "listaProduto", fetch = FetchType.LAZY)
	private List<Venda> listaVenda;
	
	public BigDecimal getTotal(int quantidade) {
	    return this.getValorVenda().multiply(new BigDecimal(quantidade));
	}

	public void removeQuantidade(long quantidadeASerRemovida) {
		this.quantidade= this.quantidade-quantidadeASerRemovida;
	}
	public void adicionaQuantidade(long quantidadeASerAdicionada) {
		this.quantidade= this.quantidade+quantidadeASerAdicionada;
	}
	public void adicionaQuantidadeAlteravalor(long quantidadeASerAdicionada, BigDecimal novoValorCusto) {
		this.quantidade= this.quantidade+quantidadeASerAdicionada;
		this.valorCusto=novoValorCusto;
	}

	public void addFornecedor(Fornecedor fornecedorPersistido) {
		fornecedores.add(fornecedorPersistido);
	}


}

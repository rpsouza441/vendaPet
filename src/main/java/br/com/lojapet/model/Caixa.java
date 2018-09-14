package br.com.lojapet.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

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
public class Caixa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy hh:mm")
	private Calendar abertoDataHora;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy hh:mm")
	private Calendar fechadoDataHora;

	private boolean isAberto = Boolean.TRUE;

	@OneToMany
	@JoinColumn(name = "caixa_movimento_id", foreignKey = @ForeignKey(name = "venda_movimento_fk"))
	private List<MovimentoDeCaixa> listaMovimentacoes = new ArrayList<MovimentoDeCaixa>();

	@ManyToOne
	@JoinColumn(name = "usuario_caixa_id", foreignKey = @ForeignKey(name = "usuario_caixa_fk"))
	private User user;

	public void addMovimentacao(MovimentoDeCaixa m) {
		listaMovimentacoes.add(m);
	}

	public void addListaMovimentacao(List<MovimentoDeCaixa> movimentoDeCaixaPersistido) {
		for (MovimentoDeCaixa movimentoDeCaixa : movimentoDeCaixaPersistido) {
			addMovimentacao(movimentoDeCaixa);
		}
	}

}

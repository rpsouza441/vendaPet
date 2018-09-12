package br.com.lojapet.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetails;

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
@Table(uniqueConstraints = @UniqueConstraint(name = "UK_por_username", columnNames = { "username" }))
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@NotEmpty
	private String nome;

	@DateTimeFormat
	private Calendar ultimoLogin;

	@OneToMany
	@JoinColumn(name = "usuario_venda_id", foreignKey = @ForeignKey(name = "venda_usuario_fk"))
	private List<Venda> listaVenda = new ArrayList<>();

	@OneToMany
	@JoinColumn(name = "usuario_compra_id", foreignKey = @ForeignKey(name = "compra_usuario_fk"))
	private List<Compra> listaCompra = new ArrayList<>();

	@OneToMany
	@JoinColumn(name = "usuario_caixa_id", foreignKey = @ForeignKey(name = "caixa_usuario_fk"))
	private List<Caixa> listaDeCaixa;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), indexes = {
			@Index(name = "user_fk", columnList = "user_id"), @Index(name = "role_fk", columnList = "role_id") })
	private List<Role> authorities = new ArrayList<Role>();

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void addVenda(Venda vendaPersistida) {
		listaVenda.add(vendaPersistida);
	}

	public void addCaixa(Caixa caixa) {
		listaDeCaixa.add(caixa);
	}

	public void addCompra(Compra compraPersistida) {
		listaCompra.add(compraPersistida);
	}

}

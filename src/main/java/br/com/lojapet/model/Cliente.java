package br.com.lojapet.model;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

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
public class Cliente {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@NotEmpty
	private String nomeCompleto;

	@DateTimeFormat
	private Calendar dataNascimento;
	private String sexo;
	private String telefone;
	private String celular;
	@Email
	private String email;

	private String cep;

	private String rua;
	private int numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	
	private boolean tipoPessoa = Boolean.TRUE;

	@CPF
	private String cpf;
	@CNPJ
	private String cnpj;
	
	@DateTimeFormat
	private Calendar dataCadastro;

	private String observacoes;
	
	private boolean clienteSemNome = Boolean.FALSE;


	@OneToMany
	@JoinColumn(name = "cliente_venda_id", foreignKey = @ForeignKey(name = "cliente_venda_fk"))
	private List<Venda> vendas;
	
//	@OneToMany
//	@JoinColumn(name = "cliente_divida_id", foreignKey = @ForeignKey(name = "cliente_divida_fk"))
//	private List<ContasAReceber> divida;

	public void setCpf(String cpf) {
		if (cpf != null && cpf.length() == 0) {
			System.out.println(" empty"+cpf);
			this.cpf = null;
		} else {
			System.out.println("not empty"+cpf);
			this.cpf = cpf;
		}
	}

	public void setCnpj(String cnpj) {
		if (cnpj != null && cnpj.length() == 0) {
			this.cnpj = null;
		} else {
			this.cnpj = cnpj;
		}
	}

}

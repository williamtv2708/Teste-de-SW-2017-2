package br.upf.topicos.industria.entidades;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import static javax.persistence.GenerationType.SEQUENCE;


/**
 * Entity implementation class for Entity: Pessoa
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Pessoa implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "PessoaId")
	@SequenceGenerator(name = "PessoaId", sequenceName = "PessoaId", allocationSize = 1)
	private Integer id;
	@NotEmpty(message="O nome deve ser Informado")
	@Column(length=60)
	@Length(min=2, max=60)
	private String nome;
	@NotEmpty(message="O endereço deve ser Informado")
	@Column(length=60)
	private String endereco;
	@Column(length=10)
	private String numero;
	@Column(length=40)
	private String complemento;
	@Column(length=40)
	private String bairro;
	@Column(length=10)
	private String cep;
	@Email
	@Column(length=80)
	private String email;
	@Column(length=20)
	private String telefone;
	@NotNull(message="Informe a Cidade")
	@ManyToOne
	private Cidade cidade;
	private static final long serialVersionUID = 1L;

	public Pessoa() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}   
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}   
	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}   
	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}   
	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}   
	public Cidade getCidade() {
		return this.cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
   
}

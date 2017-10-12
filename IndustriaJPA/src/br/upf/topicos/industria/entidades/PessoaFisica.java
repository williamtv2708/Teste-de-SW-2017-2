package br.upf.topicos.industria.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

/**
 * Entity implementation class for Entity: PessoaFisica
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class PessoaFisica extends Pessoa implements Serializable {

	@CPF
	@NotEmpty(message="O CPF deve ser informado")
	@Column(length=14)
	@Length(min=14,max=14)
	private String cpf;
	@NotEmpty(message="O RG deve ser informado")
	@Column(length=20)
	private String rg;
	@NotNull(message="A data deve ser Informada")
	@Past(message="A data deve estar no passado")
	private Date dataNascimento;
	private static final long serialVersionUID = 1L;

	public PessoaFisica() {
		super();
	}   
	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}   
	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}   
	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
   
}

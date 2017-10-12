package br.upf.topicos.industria.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

/**
 * Entity implementation class for Entity: PessoaJuridica
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class PessoaJuridica extends Pessoa implements Serializable {

	@NotEmpty(message="O CNPJ Deve ser informado")
	@CNPJ
	@Column(length=18)
	@Length(min=18, max=18)
	private String cnpj;
	@NotEmpty(message="A Inscrição Estadula deve ser Informada")
	@Column(length=15)
	@Length(min=15, max=15)
	private String ie;
	@NotNull(message="A data deve ser Informada")
	@Past(message="A data deve estar no Passado")
	private Date dataConstituicao;
	private static final long serialVersionUID = 1L;

	public PessoaJuridica() {
		super();
	}   
	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}   
	public String getIe() {
		return this.ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}   
	public Date getDataConstituicao() {
		return this.dataConstituicao;
	}

	public void setDataConstituicao(Date dataConstituicao) {
		this.dataConstituicao = dataConstituicao;
	}
   
}

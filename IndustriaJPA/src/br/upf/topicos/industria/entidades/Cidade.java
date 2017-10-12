package br.upf.topicos.industria.entidades;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Entity implementation class for Entity: Cidade
 *
 */
@Entity

public class Cidade implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "CidadeId")
	@SequenceGenerator(name = "CidadeId", sequenceName = "CidadeId", allocationSize = 1)
	private Integer id;
	@NotEmpty(message="Informe o Nome")
	private String nome;
	@NotEmpty(message="Informe a UF")
	private String uf = "RS";
	private static final long serialVersionUID = 1L;

	public Cidade() {
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
	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
   
}

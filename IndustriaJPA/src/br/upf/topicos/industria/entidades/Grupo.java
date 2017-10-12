package br.upf.topicos.industria.entidades;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Entity implementation class for Entity: Grupo
 *
 */
@Entity

public class Grupo implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "GrupoId")
	@SequenceGenerator(name = "GrupoId", sequenceName = "GrupoId", allocationSize = 1)
	private Integer id;
	@Column(length = 60)
	@NotEmpty(message="O nome é obrigatório")
	@Length(min=2, max=60, message="O nome deve ter de 2 a 60 caracterse")
	private String nome;
	private static final long serialVersionUID = 1L;

	public Grupo() {
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
   
}

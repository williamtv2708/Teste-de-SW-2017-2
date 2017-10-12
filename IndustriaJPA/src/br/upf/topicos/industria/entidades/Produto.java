package br.upf.topicos.industria.entidades;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: Produto
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Produto implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "ProdutoId")
	@SequenceGenerator(name = "ProdutoId", sequenceName = "ProdutoId", allocationSize = 1)
	private Integer id;
	@Column(length = 60)
	@NotEmpty(message="O nome é obrigatório")
	@Length(min=2, max=60, message="O nome deve ter de 2 a 60 caracterse")
	private String nome;
	@Column(length = 10)
	@NotEmpty(message="A unidade é obrigatório")
	private String unidadeEstoque;
	@NotNull
	@Column(updatable=false)
	private Float quantidadeEstoque;	
	@ManyToOne
	@NotNull(message="Grupo obrigatórios")
	private Grupo grupo;
	
	private static final long serialVersionUID = 1L;

	public Produto() {
		super();
		quantidadeEstoque = 0f;
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
	public String getUnidadeEstoque() {
		return this.unidadeEstoque;
	}

	public void setUnidadeEstoque(String unidadeEstoque) {
		this.unidadeEstoque = unidadeEstoque;
	}   
	public Float getQuantidadeEstoque() {
		return this.quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Float quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
   
}

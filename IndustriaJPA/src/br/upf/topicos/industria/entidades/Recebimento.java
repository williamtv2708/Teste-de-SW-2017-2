package br.upf.topicos.industria.entidades;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;

import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.CascadeType.ALL;

/**
 * Entity implementation class for Entity: Recebimento
 *
 */
@Entity

public class Recebimento implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "RecebimentoId")
	@SequenceGenerator(name = "RecebimentoId", sequenceName = "RecebimentoId", allocationSize = 1)
	private Integer id;
	@Temporal(TemporalType.TIMESTAMP)
	@Past(message="A data deve ser no passado")
	@NotNull
	private Date data;
	@NotEmpty(message="Informe o número da Nota")
	@Column(length=20)
	private String numeroNota;
	@NotNull
	@Column(length=20)
	private Float totalProdutos;
	@NotNull
	@Column(length=50)
	private Float valorFrete;
	@NotNull
	@Column(length=50)
	private Float totalCompra = 0f;
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "recebimento")
	private List<RecebimentoItem> itensRecebidos;
	@ManyToOne
	@NotNull(message="Informe o Fornecedor")
	private PessoaJuridica fornecedor;
	private static final long serialVersionUID = 1L;

	public Recebimento() {
		super();
		itensRecebidos = new ArrayList<>(); // devemos instanciar o itensRecebidos sempre como um novo array list
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}   
	public String getNumeroNota() {
		return this.numeroNota;
	}

	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}   
	public Float getTotalProdutos() {
		return this.totalProdutos;
	}

	public void setTotalProdutos(Float totalProdutos) {
		this.totalProdutos = totalProdutos;
	}   
	public Float getValorFrete() {
		return this.valorFrete;
	}

	public void setValorFrete(Float valorFrete) {
		this.valorFrete = valorFrete;
	}   
	public Float getTotalCompra() {
		return this.totalCompra;
	}

	public void setTotalCompra(Float totalCompra) {
		this.totalCompra = totalCompra;
	}   
	public List<RecebimentoItem> getItensRecebidos() {
		return this.itensRecebidos;
	}

	public void setItensRecebidos(List<RecebimentoItem> itensRecebidos) {
		this.itensRecebidos = itensRecebidos;
	}
	public PessoaJuridica getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(PessoaJuridica fornecedor) {
		this.fornecedor = fornecedor;
	}
   
}

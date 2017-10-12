package br.upf.topicos.industria.entidades;

import br.upf.topicos.industria.entidades.Produto;
import java.io.Serializable;
import java.lang.Float;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: MateriaPrima
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class MateriaPrima extends Produto implements Serializable {

	@NotNull
	private Float estoqueMinimo;
	@NotNull
	private Float ultimoCusto;
	@NotNull
	private Float custoMedio;
	
	private static final long serialVersionUID = 1L;

	public MateriaPrima() {
		super();
		estoqueMinimo = 0f;
		ultimoCusto = 0f;
		custoMedio = 0f;
	}   
	public Float getEstoqueMinimo() {
		return this.estoqueMinimo;
	}

	public void setEstoqueMinimo(Float estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}   
	public Float getUltimoCusto() {
		return this.ultimoCusto;
	}

	public void setUltimoCusto(Float ultimoCusto) {
		this.ultimoCusto = ultimoCusto;
	}   
	public Float getCustoMedio() {
		return this.custoMedio;
	}

	public void setCustoMedio(Float custoMedio) {
		this.custoMedio = custoMedio;
	}
   
}

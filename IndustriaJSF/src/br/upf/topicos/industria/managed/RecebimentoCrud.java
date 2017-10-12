package br.upf.topicos.industria.managed;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.upf.topicos.industria.entidades.MateriaPrima;
import br.upf.topicos.industria.entidades.PessoaJuridica;
import br.upf.topicos.industria.entidades.Recebimento;
import br.upf.topicos.industria.entidades.RecebimentoItem;
import br.upf.topicos.industria.util.GerirFactory;
import br.upf.topicos.industria.util.TrataException;

@ManagedBean
@SessionScoped
public class RecebimentoCrud {

	private List<Recebimento> lista;
	private Recebimento objeto;
	// criamos os atributos para Item do Recebimento
	private RecebimentoItem item;
	private Integer rowIndex;

	public String incluir() {
		objeto = new Recebimento();
		return "RecebimentoForm?faces-redirect=true";
	}

	public String alterar(Integer id) {
		EntityManager em = GerirFactory.getEntityManager();
		objeto = em.find(Recebimento.class, id);
		em.close();
		return "RecebimentoForm?faces-redirect=true";
	}

	public String excluir(Integer id) {
		EntityManager em = GerirFactory.getEntityManager();

		try {
			em.getTransaction().begin();
			em.remove(em.find(Recebimento.class, id));
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro ", new FacesMessage(TrataException.getErrorMessage(e)));
			return ""; // limpa a requisição
		} finally {
			em.close();
		}

		return "RecebimentoList?faces-redirect=true";
	}

	public String gravar() {
		Integer idAnt = objeto.getId();

		EntityManager em = GerirFactory.getEntityManager();
		try {
			em.getTransaction().begin();
			objeto = em.merge(objeto);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro ", new FacesMessage(TrataException.getErrorMessage(e)));
			objeto.setId(idAnt);
			return "";
		} finally {
			em.close();
		}

		return "RecebimentoList?faces-redirect=true";
	}

	public String cancelar() {
		return "RecebimentoList?faces-redirect=true";
	}

	public void inicializarLista() {
		EntityManager em = GerirFactory.getEntityManager();
		lista = em.createQuery("from Recebimento").getResultList();
		em.close();
	}

	// metodo para buscar e listar as pessoas juridicas
	public List<PessoaJuridica> completeFornecedor(String query) {

		EntityManager em = GerirFactory.getEntityManager();
		List<PessoaJuridica> results = em.createQuery("from PessoaJuridica where upper(nome)like" + "'"
				+ query.trim().toUpperCase() + "%'" + " order by nome").getResultList();
		em.close();
		return results;
	}

	
	public List<Recebimento> getLista() {
		return lista;
	}

	public void setLista(List<Recebimento> lista) {
		this.lista = lista;
	}

	public Recebimento getObjeto() {
		return objeto;
	}

	public void setObjeto(Recebimento objeto) {
		this.objeto = objeto;
	}

	/****************************************************************/
	//Metodos do Item do recebimento
	
	public void incluirItem() {
		rowIndex = null;
		item = new RecebimentoItem();
	}

	public void alterarItem() {
		this.rowIndex = null;
		item = objeto.getItensRecebidos().get(rowIndex); // pega o item coleção
	}

	public void excluirItem() {
		objeto.getItensRecebidos().remove(rowIndex.intValue()); // remove da coleção
		calcularTotais();
	}

	public void gravarItem() {
		if (this.rowIndex == null) {
			item.setRecebimento(objeto);
			objeto.getItensRecebidos().add(item); // adiciona item na coleção
		} else {
			objeto.getItensRecebidos().set(rowIndex, item); // altera na coleção
		}
		calcularTotais();
		rowIndex = null;
		item = null;
	}
	
	public String cancelarItem() {
		return "RecebimentoList?faces-redirect=true";
	}


	/*
	 * Metodo chamado por ajxa para calculo do total do item ao informar no formulario
	 */
	public void calcularTotalItem() {
		if (item.getValorUnitario() == null || item.getQuantidade() == null)
			return;
		item.setTotal(item.getValorUnitario() * item.getQuantidade());
	}

	/*
	 * Metoddo que calcula o total do recebimento após as operações sobre os itens
	 */
	public void calcularTotais() {
		Float totalProdutos = (float) 0.0;
		for (RecebimentoItem it : objeto.getItensRecebidos()) 
			totalProdutos += it.getTotal();
			objeto.setTotalProdutos(totalProdutos);
		if(objeto.getTotalCompra() == null)
			objeto.setTotalCompra((float) 0.0);
		objeto.setTotalCompra(objeto.getTotalCompra() + objeto.getTotalProdutos());

	}
	
	
	// metodo para buscar e listar as Materia Prima
	public List<MateriaPrima> completeMateriaPrima(String query) {

		EntityManager em = GerirFactory.getEntityManager();
		List<MateriaPrima> results = em.createQuery("from MateriaPrima where upper(nome)like" + "'"
				+ query.trim().toUpperCase() + "%'" + " order by nome").getResultList();
		em.close();
		return results;
	}
	
	
	public RecebimentoItem getItem() {
		return item;
	}

	public void setItem(RecebimentoItem item) {
		this.item = item;
	}

	public Integer getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}

}

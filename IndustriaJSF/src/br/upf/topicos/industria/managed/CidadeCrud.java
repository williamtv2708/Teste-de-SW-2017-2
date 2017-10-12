package br.upf.topicos.industria.managed;

import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.upf.topicos.industria.entidades.Cidade;
import br.upf.topicos.industria.util.GerirFactory;
import br.upf.topicos.industria.util.TrataException;

@ManagedBean
@SessionScoped
public class CidadeCrud {

	private List<Cidade> lista;
	private Cidade objeto;

	public String incluir() {
		objeto = new Cidade();
		return "CidadeForm?faces-redirect=true";
	}

	public String alterar(Integer id) {
		EntityManager em = GerirFactory.getEntityManager();
		objeto = em.find(Cidade.class, id);
		em.close();
		return "CidadeForm?faces-redirect=true";
	}

	public String excluir(Integer id) {
		EntityManager em = GerirFactory.getEntityManager();

		try {
			em.getTransaction().begin();
			em.remove(em.find(Cidade.class, id));
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro ", new FacesMessage(TrataException.getErrorMessage(e)));
			return ""; // limpa a requisição
		} finally {
			em.close();
		}

		return "CidadeList?faces-redirect=true";
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

		return "CidadeList?faces-redirect=true";
	}

	public String cancelar() {
		return "CidadeList?faces-redirect=true";
	}

	public void inicializarLista() {
		EntityManager em = GerirFactory.getEntityManager();
		lista = em.createQuery("from Cidade").getResultList();
		em.close();
	}

	public List<Cidade> getLista() {
		return lista;
	}

	public void setLista(List<Cidade> lista) {
		this.lista = lista;
	}

	public Cidade getObjeto() {
		return objeto;
	}

	public void setObjeto(Cidade objeto) {
		this.objeto = objeto;
	}

}

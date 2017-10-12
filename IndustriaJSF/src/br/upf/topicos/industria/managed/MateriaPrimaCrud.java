package br.upf.topicos.industria.managed;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import br.upf.topicos.industria.entidades.Grupo;
import br.upf.topicos.industria.entidades.MateriaPrima;
import br.upf.topicos.industria.util.GerirFactory;
import br.upf.topicos.industria.util.TrataException;

@ManagedBean
@SessionScoped
public class MateriaPrimaCrud {
	
	private List<MateriaPrima> lista;
	private MateriaPrima objeto;
	
	public String incluir() {
		
		objeto = new MateriaPrima();
		return "MateriaPrimaForm?faces-redirect=true";
	}

	public String alterar(Integer id) {
		EntityManager em = GerirFactory.getEntityManager();
		objeto = em.find(MateriaPrima.class, id);
		em.close();
		return "MateriaPrimaForm?faces-redirect=true";
	}

	public String excluir(Integer id) {
		EntityManager em = GerirFactory.getEntityManager();

		try {
			em.getTransaction().begin();
			em.remove(em.find(MateriaPrima.class, id));
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro ", new FacesMessage(TrataException.getErrorMessage(e)));	
			return ""; // limpa a requisição
		} finally {
			em.close();
		}

		return "MateriaPrimaList?faces-redirect=true";
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

		return "MateriaPrimaList?faces-redirect=true";
	}

	public String cancelar() {
		return "MateriaPrimaList?faces-redirect=true";
	}

	public void inicializarLista() {
		EntityManager em = GerirFactory.getEntityManager();
		lista = em.createQuery("from MateriaPrima").getResultList();
		em.close();
	}
	
	
	public List<Grupo> completeGrupo(String query) {

		EntityManager em = GerirFactory.getEntityManager();
		List<Grupo> results = em.createQuery("from Grupo where upper(nome)like" + "'" + query.trim().toUpperCase() + "%'" + " order by nome").getResultList();
		em.close();
		return results;
	}	

	
	public List<MateriaPrima> getLista() {
		return lista;
	}
	public void setLista(List<MateriaPrima> lista) {
		this.lista = lista;
	}
	public MateriaPrima getObjeto() {
		return objeto;
	}
	public void setObjeto(MateriaPrima objeto) {
		this.objeto = objeto;
	}
	
	
	
	

}

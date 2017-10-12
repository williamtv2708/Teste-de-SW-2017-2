package br.upf.topicos.industria.managed;

import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import br.upf.topicos.industria.entidades.Cidade;
import br.upf.topicos.industria.entidades.PessoaJuridica;
import br.upf.topicos.industria.util.GerirFactory;
import br.upf.topicos.industria.util.TrataException;


@ManagedBean
@SessionScoped
public class PessoaJuridicaCrud{

	private List<PessoaJuridica> lista;
	private PessoaJuridica objeto;

	public String incluir() {
		
			objeto = new PessoaJuridica();
	
		return "PessoaJuridicaForm?faces-redirect=true";
	}

	public String alterar(Integer id) {
		EntityManager em = GerirFactory.getEntityManager();
		objeto = em.find(PessoaJuridica.class, id);
		em.close();
		return "PessoaJuridicaForm?faces-redirect=true";
	}

	public String excluir(Integer id) {
		EntityManager em = GerirFactory.getEntityManager();

		try {
			em.getTransaction().begin();
			em.remove(em.find(PessoaJuridica.class, id));
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro ", new FacesMessage(TrataException.getErrorMessage(e)));
			return ""; // limpa a requisição
		} finally {
			em.close();
		}

		return "PessoaJuridicaList?faces-redirect=true";
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

		return "PessoaJuridicaList?faces-redirect=true";
	}

	public String cancelar() {
		return "PessoaJuridicaList?faces-redirect=true";
	}

	public void inicializarLista() {
		EntityManager em = GerirFactory.getEntityManager();
		lista = em.createQuery("from PessoaJuridica ").getResultList();
		em.close();
	}


	public List<Cidade> completeCidade(String query) {

		EntityManager em = GerirFactory.getEntityManager();
		List<Cidade> results = em.createQuery("from Cidade where upper(nome)like" + "'" + query.trim().toUpperCase() + "%'" + " order by nome").getResultList();
		em.close();
		return results;
	}	
	

	public List<PessoaJuridica> getLista() {
		return lista;
	}

	public void setLista(List<PessoaJuridica> lista) {
		this.lista = lista;
	}

	public PessoaJuridica getObjeto() {
		return objeto;
	}

	public void setObjeto(PessoaJuridica objeto) {
		this.objeto = objeto;
	}
}

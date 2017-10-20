package br.upf.topicos.industria.managed;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import br.upf.topicos.industria.util.GerirFactory;

public class Test {

	@org.junit.Test
	public void testVeiculo() {
		
		Login objeto = new Login();
		EntityManager em = GerirFactory.getEntityManager();
		
		objeto.setUsername("usuario");
		objeto.setPassword("william20");
		
		try {
			em.getTransaction().begin();
			em.getTransaction().commit();
			System.out.println("Login");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();	
		}

	}
}
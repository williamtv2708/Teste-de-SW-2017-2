package br.upf.topicos.industria.teste;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import br.upf.topicos.industria.managed.Login;
import br.upf.topicos.industria.util.GerirFactory;

public class TesteReleaseLogin {
	
	Login login = new Login();
	EntityManager em = GerirFactory.getEntityManager();

	@org.junit.Test
	public void TesteLogin(){
		//iniciando a conexão
		em.getTransaction().begin();
		
		CampoLogin();
		
		//chama temporizador
		sleep();
		
		CampoSenha();
		
		//fechando conexão
		em.getTransaction().commit();
	}
	
	
	
	public void CampoLogin() {
		
		//setando valor no campo usuário
//		login.setUsername("usuario");
		
		if(login.getUsername().equals("usuario")){
			try {
				System.out.println("Campo Usuário ok!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("Campo Usuário com erro!");
		}
	}
	
	public void CampoSenha(){

		//setando valor no campo senha
//		login.setPassword("william20");
		
//		sitex
		
		if(login.getPassword().equals("william20")){
			try {
				System.out.println("Campo Senha ok!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("Campo Senha com erro!");
		}
	}
	
	//temporizador em 5 segundos
	public void sleep(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
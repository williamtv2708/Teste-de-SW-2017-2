package br.upf.topicos.industria.testes;

import javax.persistence.Persistence;

import br.upf.topicos.industria.entidades.MateriaPrima;



public class TesteJPA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Persistence.createEntityManagerFactory("IndustriaJPA");

	}

}

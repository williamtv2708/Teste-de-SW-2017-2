package br.upf.topicos.industria.testes;

import javax.persistence.Persistence;

public class TesteJPA {
	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("IndustriaJPA");
	}
}
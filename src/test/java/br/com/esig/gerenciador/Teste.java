package br.com.esig.gerenciador;

import javax.persistence.Persistence;

public class Teste {

	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("gerenciador");
	}

}

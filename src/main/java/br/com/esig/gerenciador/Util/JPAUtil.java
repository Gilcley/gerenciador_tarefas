package br.com.esig.gerenciador.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory factory = null;

	static {
		if (factory == null)
			factory = Persistence.createEntityManagerFactory("gerenciador");
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}

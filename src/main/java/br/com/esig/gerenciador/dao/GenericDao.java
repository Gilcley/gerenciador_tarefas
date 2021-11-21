package br.com.esig.gerenciador.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.esig.gerenciador.Util.JPAUtil;

/**
 * Dao genérico utilizado para realizar persistência, consulta e atualização de dados.
 * 
 * @author Gilcley Carvalho
 * */
public class GenericDao<E> {

	/**
	 * Salva o obj passada na base.
	 * 
	 *  @param obj
	 */
	public void salvar(E obj) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.persist(obj);
		
		entityTransaction.commit();
		
		entityManager.close();
	}
	
	/**
	 * Atualiza o obj passado na base.
	 * 
	 *  @param obj
	 */
	public void update(E obj) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.merge(obj);
		
		entityTransaction.commit();
		
		entityManager.close();
	}
	
	/**
	 * Retorna uma lista com todos os registros da entidade informada.
	 * 
	 *  @param entidade
	 *  @return {@link List}
	 */
	@SuppressWarnings("unchecked")
	public List<E> findAll(Class<E> entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		List<E> result = entityManager.createQuery("from "+ entidade.getName()).getResultList();
		
		entityManager.close();
		
		return result;
	}
}

package br.com.esig.gerenciador.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.esig.gerenciador.Util.JPAUtil;
import br.com.esig.gerenciador.dominio.Pessoa;
import br.com.esig.gerenciador.dominio.Tarefa;

/**
 * Dao utilizado para operações com Tarefas.
 * 
 * @author Gilcley Carvalho
 * */
public class TarefaDao extends GenericDao<Tarefa>{

	/**
	 * Retorna uma lista de tarefas de acordo com os parâmentros informados.
	 * 
	 * @param titulo
	 * @param numero
	 * @param idSituacao
	 * @param responsavel
	 * @return {@link List}
	 * */
	@SuppressWarnings("unchecked")
	public List<Tarefa> findByFilters(String titulo, Integer numero, int idSituacao, Pessoa responsavel){
		StringBuilder consulta = new StringBuilder();
		consulta.append(" FROM Tarefa t ");
		consulta.append(" WHERE t.ativo = true ");
		
		if(titulo != null && !titulo.isEmpty())
			consulta.append(" AND upper(t.titulo) like '%"+titulo.toUpperCase()+"%'");
		if(numero != null && numero > 0)
			consulta.append(" AND t.numero = "+numero);
		if(idSituacao > 0)
			consulta.append(" AND t.situacao.id = "+idSituacao);
		if(responsavel != null && responsavel.getId() > 0)
			consulta.append(" AND t.responsavel.id = "+responsavel.getId());
		
		consulta.append(" ORDER BY t.numero ");
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		return entityManager.createQuery(consulta.toString()).getResultList();
	}
}

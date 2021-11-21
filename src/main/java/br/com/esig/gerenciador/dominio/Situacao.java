package br.com.esig.gerenciador.dominio;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Gilcley Carvalho
 * */
@Entity
public class Situacao {

	public static final int EM_ANDAMENTO = 1;
	
	public static final int CONCLUIDA = 2;
	
	@Id
	private int id;
	
	private String descricao;
	
	public Situacao() {}
	
	public Situacao(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}

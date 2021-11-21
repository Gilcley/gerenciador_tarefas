package br.com.esig.gerenciador.dominio;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Gilcley Carvalho
 * */
@Entity
public class Prioridade {
	
	@Id
	private int id;
	
	private String descricao;

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

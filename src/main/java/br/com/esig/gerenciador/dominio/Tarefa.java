package br.com.esig.gerenciador.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * @author Gilcley Carvalho
 * */
@Entity
public class Tarefa{
 
	/** Armazena a chave primária do registro*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/** Armazena o título da tarefa.*/
	private String titulo;
	
	/** Armazena a descrição da tarefa. */
	private String descricao;
	
	/** Indica se o registro está ativo na base de dados.*/
	private boolean ativo;
	
	/** Pessoa responsável pela tarefa*/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pessoa", nullable = false)
	private Pessoa responsavel;
	
	/** Prioridade da tarefa */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_prioridade", nullable = false)
	private Prioridade prioridade;
	
	/** Situação da tarefa */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_situacao", nullable = false)
	private Situacao situacao;
	
	/** Prazo final para realização da tarefa*/
	@Temporal(TemporalType.DATE)
	private Date deadline;

	/** Número  gerado automaticamente de forma serial para identificação da tarefa. */
	@Column(name="numero", columnDefinition="serial")
	@Generated(GenerationTime.INSERT)
	private int numero;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Pessoa getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Pessoa responsavel) {
		this.responsavel = responsavel;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	/**
	 * Indica se é permitido realizar a atualização da situação de uma tarefa para concluído.
	 * 
	 * @return {@link Boolean}
	 * */
	public boolean isPermiteConcluir() {
		return situacao.getId() == Situacao.EM_ANDAMENTO;
	}
	
}

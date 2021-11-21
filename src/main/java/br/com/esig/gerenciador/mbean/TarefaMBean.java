package br.com.esig.gerenciador.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.esig.gerenciador.dao.GenericDao;
import br.com.esig.gerenciador.dao.TarefaDao;
import br.com.esig.gerenciador.dominio.Pessoa;
import br.com.esig.gerenciador.dominio.Prioridade;
import br.com.esig.gerenciador.dominio.Situacao;
import br.com.esig.gerenciador.dominio.Tarefa;

/**
 * ManagedBean responsável pelo gerenciamento das operações de Tarefas.
 * 
 * @author Gilcley Carvalho
 * */
@SessionScoped
@ManagedBean(name = "tarefaMbean")
public class TarefaMBean {

	/** Nome das páginas de navegação*/
	public static final String FORM = "form";
	public static final String LIST = "list";
	public static final String HOME = "index";

	private Tarefa tarefa;
	
	private List<Tarefa> tarefas;
	
	private Integer numero = null;
	
	private String descricaoOperacao;
	
	/**Inicializa os atributos. */
	private void init() {
		tarefa = new Tarefa();
		tarefa.setPrioridade(new Prioridade());
		tarefa.setResponsavel(new Pessoa());
		tarefa.setSituacao(new Situacao());
	}
	
	/**
	 * Prepara para o mbean para realizar um cadastro.
	 * 
	 * @return {@link String}
	 * */
	public String iniciarCadastro() {
		init();
		setDescricaoOperacao("Cadastrar");
		return FORM;
	}
	
	/**
	 * Prepara para o mbean para realizar uma alteração.
	 * 
	 * @return {@link String}
	 * */
	public String iniciarAlteracao(Tarefa tarefa){
		this.tarefa = tarefa; 
		setDescricaoOperacao("Atualizar");
		return FORM;
	}
	
	
	/**
	 * Cadastra ou atualiza uma tarefa.
	 * 
	 * @return {@link String}
	 * */
	public String salvar(){
		TarefaDao dao = new TarefaDao();
		
		boolean cadastro = tarefa.getId() == 0;
		
		if(cadastro) {
			tarefa.setAtivo(true);
			tarefa.setSituacao(new Situacao(Situacao.EM_ANDAMENTO));
			dao.salvar(tarefa);
			addMensagemInfo("Tarefa cadastrada com sucesso!");
			return HOME;
		}else {
			dao.update(tarefa);
			addMensagemInfo("Tarefa atualizada com sucesso!");
			init();
			return buscar();
		}
	}
	
	/**
	 * Atualiza a situação de uma tarefa para concluída.
	 * 
	 * @return {@link String}
	 * */
	public String concluir(Tarefa tarefa) {
		tarefa.getSituacao().setId(Situacao.CONCLUIDA);
		TarefaDao dao = new TarefaDao();
		dao.update(tarefa);
		addMensagemInfo("Operação realizada com sucesso!");
		return buscar();
	}
	
	/**
	 * Realiza a exclusão de uma tarefa atualizando ativo para false.
	 * 
	 * @return {@link String}
	 * */
	public String excluir(Tarefa tarefa){
		tarefa.setAtivo(false);
		TarefaDao dao = new TarefaDao();
		dao.update(tarefa);
		addMensagemInfo("Operação realizada com sucesso!");
		return buscar();
	}
	
	/**
	 * Lista as tarefas em andamento.
	 * 
	 * @return {@link String}
	 * */
	public String listar() {
		init();
		tarefa.setSituacao(new Situacao(Situacao.EM_ANDAMENTO));
		return buscar();
	}
	
	/**
	 * Realiza a busca das tarefas cadastradas.
	 * 
	 * @return {@link String}
	 * */
	public String buscar(){
		TarefaDao dao = new TarefaDao();
		tarefas = dao.findByFilters(tarefa.getTitulo(), numero, tarefa.getSituacao().getId(), tarefa.getResponsavel());
		return LIST;
	}
	
	public String inicio(){
		return HOME;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	/**
	 * Monta uma lista de @SelectItem com id e descrição de todas as prioridades cadastradas.
	 * 
	 * @return {@link List}
	 * */
	public List<SelectItem> getPrioridades(){
		GenericDao<Prioridade> dao = new GenericDao<>();
		List<Prioridade> prioridades = dao.findAll(Prioridade.class);
		List<SelectItem> result = new ArrayList<>();
		
		prioridades.forEach(p -> result.add(new SelectItem(p.getId(), p.getDescricao())));
		
		return result;
	}
	
	/**
	 * Monta uma lista de @SelectItem com id e descrição de todas as situações cadastradas.
	 * 
	 * @return {@link List}
	 * */
	public List<SelectItem> getSituacoes(){
		GenericDao<Situacao> dao = new GenericDao<>();
		List<Situacao> situacoes = dao.findAll(Situacao.class);
		List<SelectItem> result = new ArrayList<>();
		
		situacoes.forEach(s -> result.add(new SelectItem(s.getId(), s.getDescricao())));
		
		return result;
	}
	
	/**
	 * Monta uma lista de @SelectItem com id e nome de todas as pessoas cadastradas.
	 * 
	 * @return {@link List}
	 * */
	public List<SelectItem> getPessoas(){
		GenericDao<Pessoa> dao = new GenericDao<>();
		List<Pessoa> pessoas = dao.findAll(Pessoa.class);
		List<SelectItem> result = new ArrayList<>();
		
		pessoas.forEach(p -> result.add(new SelectItem(p.getId(), p.getNome())));
		
		return result;
	}
	
	/**
	 * Lança mensagem de informação na tela
	 * */
	public static void addMensagemInfo(String mensagem){
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getDescricaoOperacao() {
		return descricaoOperacao;
	}

	public void setDescricaoOperacao(String descricaoOperacao) {
		this.descricaoOperacao = descricaoOperacao;
	}
	
}

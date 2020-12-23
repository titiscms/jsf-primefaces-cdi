package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Autor;

public class AutorDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// atributos
	@Inject
	private EntityManager manager;
	private DAO<Autor> dao;
	
	// método de inicialização
	@PostConstruct
	void init() {
		this.dao = new DAO<Autor>(this.manager, Autor.class);
	}
	
	// métodos
	/**
	 * Método para adicionar um {@link Autor} na base de dados
	 * @param autor
	 */
	public void adiciona(Autor autor) {
		dao.adiciona(autor);
	}

	/**
	 * Método para remover um {@link Autor} na base de dados
	 * @param autor
	 */
	public void remove(Autor autor) {
		dao.remove(autor);
	}

	/**
	 * Método para atualizar um {@link Autor} na base de dados
	 * @param autor
	 */
	public void atualiza(Autor autor) {
		dao.atualiza(autor);
	}

	/**
	 * Método para recuperar uma lista de {@link Autor} na base de dados
	 * @return
	 */
	public List<Autor> listaTodos() {
		return dao.listaTodos();
	}

	/**
	 * Método para buscar um {@link Autor} na base de dados passando o id do autor
	 * @param id
	 * @return
	 */
	public Autor buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

}

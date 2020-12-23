package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.transaction.Transacional;
import br.com.caelum.livraria.util.RedirectView;

@Named
@ViewScoped
public class AutorBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// atributos 
	private Autor autor = new Autor();
	private Integer autorId;
	@Inject
	private AutorDao autorDao;
		
	// getters e setters
	public Integer getAutorId() {
		return autorId;
	}
	
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public Autor getAutor() {
		return autor;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	
	// métodos
	/**
	 * Método usado para gravar um autor
	 * @return
	 */
	@Transacional
	public RedirectView gravar() {
		System.out.println("Inicio do método gravar()");
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			this.autorDao.adiciona(this.autor);
		} else {
			this.autorDao.atualiza(this.autor);
		}
		
		this.autor = new Autor();
		
		System.out.println("Fim do método gravar()");
//		return "livro?faces-redirect=true";
		return new RedirectView("livro");
	}
	
	/**
	 * Método para recuperar uma lista de autores da base de dados
	 * @return
	 */
	public List<Autor> getAutores() {
		System.out.println("Inicio do método getAutores()");
		
		List<Autor> autores = this.autorDao.listaTodos();
		
		System.out.println("Fim do método getAutores()");
		return autores;
	}
	
	/**
	 * Método para deletar um autor da base de dados
	 * @param autor
	 */
	@Transacional
	public void remover(Autor autor) {
		System.out.println("Inicio do método remover()");
		
		System.out.println("Fim do método remover()");
		this.autorDao.remove(autor);
	}
	
	/**
	 * Método para recuperar os dados de um autor quando for realizar a edição do mesmo
	 * @param autor
	 */
	public void carregar(Autor autor) {
		System.out.println("Inicio do método carregar()");
		
		System.out.println("Fim do método carregar()");
		this.autor = autor;
	}
	
	/** 
	 * Método para recuperar os dados de um autor quando for realizar a edição do mesmo
	 * passando o autorId como query params
	 */
	public void carregarAutorPorId() {
		System.out.println("Inicio do método carregarAutorPorId()");
		
		System.out.println("Fim do método carregarAutorPorId()");
		this.autor = this.autorDao.buscaPorId(this.autorId);
	}
	
}

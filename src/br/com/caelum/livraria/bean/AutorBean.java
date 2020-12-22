package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class AutorBean {
	
	// atributos 
	private Autor autor = new Autor();
	private Integer autorId;
	
	
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
	public RedirectView gravar() {
		System.out.println("Inicio do método gravar()");
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
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
		
		DAO<Autor> dao = new DAO<Autor>(Autor.class);
		
		List<Autor> autores = dao.listaTodos();
		
		System.out.println("Fim do método getAutores()");
		return autores;
	}
	
	/**
	 * Método para deletar um autor da base de dados
	 * @param autor
	 */
	public void remover(Autor autor) {
		System.out.println("Inicio do método remover()");
		
		DAO<Autor> dao = new DAO<Autor>(Autor.class);
		
		System.out.println("Fim do método remover()");
		dao.remove(autor);
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
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
	}
	
}

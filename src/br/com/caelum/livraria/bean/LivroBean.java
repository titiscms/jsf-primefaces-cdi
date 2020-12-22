package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

	// atributos
	private static final long serialVersionUID = 1L;
	private Livro livro = new Livro();
	private Integer autorId;
	private Integer livroId;
	private List<Livro> livros;
	
	// getters e setters
	public Livro getLivro() {
		return livro;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
		
	public Integer getAutorId() {
		return autorId;
	}
	
	public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }
	
	public Integer getLivroId() {
		return livroId;
	}
	
	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}
	
	// métodos
	/**
	 * Método para recuperar os livros da base de dados
	 * @return
	 */
	public List<Livro> getLivros() {
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		
		if (this.livros == null) {
			this.livros = dao.listaTodos();
		}
		
		return livros;
	}

	/**
	 * Método para salvar um livro na base de dados
	 */
	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", 
					new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}

		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		
		if (this.livro.getId() == null) {
			dao.adiciona(this.livro);
			this.livros = dao.listaTodos();
		} else {
			dao.atualiza(this.livro);
		}
		
		this.livro = new Livro();
	}
	
	/**
	 * Método para deletar um livro da base de dados
	 * @param livro
	 */
	public void remover(Livro livro) {
		System.out.println("Removendo livro " + livro.getTitulo());
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		dao.remove(livro);
		this.livros = dao.listaTodos();
	}
	
	/**
	 * Método para recuperar os dados de um livro quando for realizar a edição
	 * @param livro
	 */
	public void carregar(Livro livro) {
		System.out.println("Carregando livro " + livro.getTitulo());
		this.livro = livro;
	}
	
	/**
	 * Método para recuperar os dados de um livro quando for realizar a edição
	 * passando o livroId por query params
	 */
	public void carregarLivroPorId() {
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(livroId);
	}
	
	/**
	 * Método de validação customizada do ISBN
	 * @param fc
	 * @param component
	 * @param inputValue
	 * @throws ValidatorException
	 */
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object inputValue) throws ValidatorException {
		String valor = inputValue.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(
					new FacesMessage("ISBN deveria começar com dígito 1."));
		}
	}
	
	/**
	 * Método para fazer um redirect para o formulário do autor
	 * @return
	 */
	public String formAutor() {
		System.out.println("Chamando o formulário do autor");
		return "autor?faces-redirect=true";
	}
	
	/**
	 * Método para associar um autor num livro
	 */
	public void gravarAutor() {		
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		
		System.out.println("Gravando autor " + autor.getNome() + " no livro");
		
		this.livro.adicionaAutor(autor);
	}
	
	/**
	 * Metodo para deletar um autor de um livro especifico
	 * @param autor
	 */
	public void removerAutorDoLivro(Autor autor) {
		System.out.println("Removendo autor " + autor.getNome() + " do livro");
		this.livro.removeAutor(autor);
	}
	
	/**
	 * Método para recuperar uma lista de autores da base de dados
	 * @return
	 */
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	/**
	 * Método para buscar os autores de um livro espercifico
	 * @return
	 */
	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}
	
}

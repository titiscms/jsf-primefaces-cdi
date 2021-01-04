package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.transaction.Transacional;

@Named
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// atributos
	private Livro livro = new Livro();
	private Integer autorId;
	private Integer livroId;
	private List<Livro> livros;
	@Inject
	private LivroDao livroDao;
	@Inject
	private AutorDao autorDao;
	@Inject
	private FacesContext context;
	
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
		System.out.println("Inicio do método getLivros()");
		
		if (this.livros == null) {
			this.livros = this.livroDao.listaTodos();
		}
		
		System.out.println("Fim do método getLivros()");
		return livros;
	}

	/**
	 * Método para salvar um livro na base de dados
	 */
	@Transacional
	public void gravar() {
		System.out.println("Inicio do método gravar()");
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			context.addMessage("autor", 
					new FacesMessage("Livro deve ter pelo menos um Autor."));
			
			System.out.println("Fim do método gravar()");
			return;
		}

		if (this.livro.getId() == null) {
			this.livroDao.adiciona(this.livro);
			this.livros = this.livroDao.listaTodos();
		} else {
			this.livroDao.atualiza(this.livro);
		}
		
		System.out.println("Fim do método gravar()");
		
		this.livro = new Livro();
	}
	
	/**
	 * Método para deletar um livro da base de dados
	 * @param livro
	 */
	@Transacional
	public void remover(Livro livro) {
		System.out.println("Inicio do método remover()");
		System.out.println("Removendo livro " + livro.getTitulo());
		
		this.livroDao.remove(livro);
		
		System.out.println("Fim do método remover()");
		this.livros = this.livroDao.listaTodos();
	}
	
	/**
	 * Método para recuperar os dados de um livro quando for realizar a edição
	 * @param livro
	 */
	public void carregar(Livro livro) {
		System.out.println("Inicio do método carregar()");
		System.out.println("Carregando livro " + livro.getTitulo());
		
		System.out.println("Fim do método carregar()");
		this.livro = this.livroDao.buscaPorId(livro.getId());
	}
	
	/**
	 * Método para recuperar os dados de um livro quando for realizar a edição
	 * passando o livroId por query params
	 */
	public void carregarLivroPorId() {
		System.out.println("Inicio do método carregarLivroPorId()");
		
		Livro livro = this.livroDao.buscaPorId(livroId);
		
		System.out.println("Fim do método carregarLivroPorId()");
		
		this.livro = livro;
	}
	
	/**
	 * Método de validação customizada do ISBN
	 * @param fc
	 * @param component
	 * @param inputValue
	 * @throws ValidatorException
	 */
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object inputValue) throws ValidatorException {
		System.out.println("Inicio do método comecaComDigitoUm()");
		
		String valor = inputValue.toString();
		if (!valor.startsWith("1")) {
			System.out.println("fim do método comecaComDigitoUm()");
			
			throw new ValidatorException(
					new FacesMessage("ISBN deveria começar com dígito 1."));
		}
	}
	
	/**
	 * Método para fazer um redirect para o formulário do autor
	 * @return
	 */
	public String formAutor() {
		System.out.println("Inicio do método formAutor()");
		System.out.println("Chamando o formulário do autor");
		
		System.out.println("Fim do método formAutor()");
		return "autor?faces-redirect=true";
	}
	
	/**
	 * Método para associar um autor num livro
	 */
	@Transacional
	public void gravarAutor() {
		System.out.println("Inicio do método gravarAutor()");
		
		Autor autor = this.autorDao.buscaPorId(this.autorId);
		
		System.out.println("Gravando autor " + autor.getNome() + " no livro");
		
		System.out.println("Fim do método gravarAutor()");
		this.livro.adicionaAutor(autor);
	}
	
	/**
	 * Metodo para deletar um autor de um livro especifico
	 * @param autor
	 */
	@Transacional
	public void removerAutorDoLivro(Autor autor) {
		System.out.println("Inicio do método removerAutorDoLivro()");
		System.out.println("Removendo autor " + autor.getNome() + " do livro");
		
		System.out.println("Fim do método removerAutorDoLivro()");
		this.livro.removeAutor(autor);
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
	 * Método para buscar os autores de um livro espercifico
	 * @return
	 */
	public List<Autor> getAutoresDoLivro() {
		System.out.println("Inicio do método getAutoresDoLivro()");
		
		System.out.println("Fim do método getAutoresDoLivro()");
		return this.livro.getAutores();
	}
	
}

package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;
import br.com.caelum.livraria.util.RedirectView;

@Named
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// atributos
	private Usuario usuario = new Usuario();
	@Inject
	private UsuarioDao dao;
	@Inject
	private FacesContext context;

	// getters e setters
	public Usuario getUsuario() {
		return usuario;
	}

	// métodos
	/**
	 * Método para verificar se o usuario e a senha passados são válidos
	 * 
	 * @return
	 */
	public RedirectView efetuarLogin() {
		System.out.println("Inicio do método efetuarLogin()");
		System.out.println("Efetuando login do usuario " + this.usuario.getEmail());

		boolean existe = dao.existe(this.usuario);

		if (existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);

			System.out.println("Fim do método efetuarLogin()");
			return new RedirectView("livro");
		}

		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Email e/ou senha inválidos. Verifique e tente novamente!"));

		System.out.println("Fim do método efetuarLogin()");
		return new RedirectView("login");
	}

	/**
	 * Método para remover o usuario da sessão
	 * 
	 * @return
	 */
	public RedirectView deslogar() {
		System.out.println("Inicio do método deslogar()");

		context.getExternalContext().getSessionMap().remove("usuarioLogado");

		System.out.println("Fim do método deslogar()");
		return new RedirectView("login");
	}

}

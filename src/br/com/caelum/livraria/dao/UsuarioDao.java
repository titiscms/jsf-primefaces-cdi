package br.com.caelum.livraria.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;

public class UsuarioDao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public boolean existe(Usuario usuario) {
		try {
			TypedQuery<Usuario> query = manager.createQuery("select u from Usuario u where u.email = :email and u.senha = :senha", Usuario.class);
			
			query.setParameter("email", usuario.getEmail());
			query.setParameter("senha", usuario.getSenha());
			
			query.getSingleResult();
			
			manager.close();
			
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

}

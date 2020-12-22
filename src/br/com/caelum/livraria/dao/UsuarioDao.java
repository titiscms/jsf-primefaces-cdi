package br.com.caelum.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;

public class UsuarioDao {

	public boolean existe(Usuario usuario) {
		try {
			EntityManager em = new JPAUtil().getEntityManager();
			
			TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where u.email = :email and u.senha = :senha", Usuario.class);
			
			query.setParameter("email", usuario.getEmail());
			query.setParameter("senha", usuario.getSenha());
			
			query.getSingleResult();
			
			em.close();
			
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

}

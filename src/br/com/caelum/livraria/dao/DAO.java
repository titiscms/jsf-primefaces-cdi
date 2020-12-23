package br.com.caelum.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DAO<T> {

	private final Class<T> classe;
	private EntityManager manager;

	public DAO(EntityManager manager, Class<T> classe) {
		this.manager = manager;
		this.classe = classe;
	}

	public void adiciona(T t) {
		// persiste o objeto
		manager.persist(t);
	}

	public void remove(T t) {
		manager.remove(manager.merge(t));
	}

	public void atualiza(T t) {
		manager.merge(t);
	}

	public List<T> listaTodos() {
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = manager.createQuery(query).getResultList();

		return lista;
	}

	public T buscaPorId(Integer id) {
		T instancia = manager.find(classe, id);
		return instancia;
	}
	
	public int contaTodos() {
		long result = (Long) manager.createQuery("select count(n) from livro n")
				.getSingleResult();

		return (int) result;
	}
	
	public int quantidadeDeElementos() {
        long result = (Long) manager.createQuery("select count(n) from " + classe.getSimpleName() + " n")
                .getSingleResult();

        return (int) result;
    }

//	original
//	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
//		EntityManager em = new JPAUtil().getEntityManager();
//		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
//		query.select(query.from(classe));
//
//		List<T> lista = em.createQuery(query).setFirstResult(firstResult)
//				.setMaxResults(maxResults).getResultList();
//
//		em.close();
//		return lista;
//	}
	
	public List<T> listaTodosPaginada(int firstResult, int maxResults, String coluna, String valor) {
	    CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
	    Root<T> root = query.from(classe);

	    if(valor != null)
	        query = query.where(manager.getCriteriaBuilder().like(root.<String>get(coluna), valor + "%"));

	    List<T> lista = manager.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();

	    return lista;
	}

}

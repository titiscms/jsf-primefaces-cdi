package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Venda;

public class VendaDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public List<Venda> getVendas() {
		
		TypedQuery<Venda> query = manager.createQuery("select v from Venda v", Venda.class);
		
		List<Venda> vendas = query.getResultList();
		
		return vendas;
	}
	
}

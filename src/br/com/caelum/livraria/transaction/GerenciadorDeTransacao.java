package br.com.caelum.livraria.transaction;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@AroundInvoke
	public Object executaTransaction(InvocationContext context) throws Exception {

		// abre transacao
		System.out.println("Abrindo transação");
		manager.getTransaction().begin();
		
		// chama os daos que precisam de um transação
		Object result = context.proceed();

		// commita a transacao
		System.out.println("Fechando transação");
		manager.getTransaction().commit();
		
		return result;
	}
	
}

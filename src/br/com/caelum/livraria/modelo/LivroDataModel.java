package br.com.caelum.livraria.modelo;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dao.DAO;

public class LivroDataModel extends LazyDataModel<Livro> {

	private static final long serialVersionUID = 1L;
	
	public LivroDataModel() {
		super.setRowCount(new DAO<Livro>(Livro.class).quantidadeDeElementos());
	}
	
	@Override
	public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, FilterMeta> filtros) {
		Object tituloMeta = filtros.get("titulo");
		
		System.out.println("titulo Meta = " + tituloMeta);
		
		String titulo = null;
		
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		
	    return dao.listaTodosPaginada(inicio, quantidade, "titulo", titulo);
	}
	
}

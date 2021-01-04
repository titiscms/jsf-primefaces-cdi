package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.dao.VendaDao;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LivroDao livroDao;
	@Inject
	private VendaDao vendaDao;
	

	// métodos
	/**
	 * Método para converter as vendas no tipo {@link BarChartModel} para enviar para a view
	 * @return
	 */
	public BarChartModel getVendasModel() {
		System.out.println("Inicio do método getVendasModel()");

		BarChartModel model = new BarChartModel();
		model.setAnimate(true);
		model.setTitle("Vendas");
		model.setLegendPosition("ne");
		
		Axis xAxis = model.getAxis(AxisType.X);
	    xAxis.setLabel("Título");
	    
	    Axis yAxis = model.getAxis(AxisType.Y);
	    yAxis.setLabel("Quantidade");
		
		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel("Vendas");

		List<Venda> vendas = getVendas();
		for (Venda venda : vendas) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
		
		model.addSeries(vendaSerie);

		System.out.println("Fim do método getVendasModel()");
		return model;
	}

	/**
	 * Método utilitário para representar todas a vendas realizadas 
	 * @param seed
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<Venda> getVendasUtil(long seed) {
		System.out.println("Inicio do método getVendas()");

		List<Livro> livros = this.livroDao.listaTodos();
		List<Venda> vendas = new ArrayList<Venda>();

		Random random = new Random(seed);

		for (Livro livro : livros) {
			Integer quantidade = random.nextInt(200);
			vendas.add(new Venda(livro, quantidade));
		}

		System.out.println("Fim do método getVendas()");
		return vendas;
	}
	
	/**
	 * Método para recuperar a lista de vendas do banco de dados.
	 * @return
	 */
	private List<Venda> getVendas() {
		List<Venda> vendas = vendaDao.getVendas();
		
		return vendas;
	}
	
}

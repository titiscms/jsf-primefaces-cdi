package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class TemaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// atributos
	private String tema = "luna-amber";
	
	// getters e setters
	public String getTema() {
		return tema;
	}
	
	public void setTema(String tema) {
		this.tema = tema;
	}
	
	// métodos
	/**
	 * Método listando todos os temas disponíveis da aplicação
	 * @return
	 */
	public String[] getTemas() {
		System.out.println("Inicio do método getTemas()");
		
		String[] temas = new String[] {
				"aristo",
				"luna-amber",
				"luna-blue",
				"luna-green",
				"luna-pink",
				"nova-colored",
				"nova-dark",
				"nova-light",
				"omega",
				"afterdark", 
				"afternoon", 
				"afterwork",
	            "black-tie", 
	            "blitzer", 
	            "bluesky", 
	            "bootstrap", 
	            "casablanca",
	            "cupertino", 
	            "cruze", 
	            "dark-hive", 
	            "delta", 
	            "dot-luv",
	            "eggplant", 
	            "excite-bike", 
	            "flick", 
	            "glass-x", 
	            "home",
	            "hot-sneaks", 
	            "humanity", 
	            "le-frog", 
	            "midnight", 
	            "mint-choc",
	            "overcast", 
	            "pepper-grinder", 
	            "redmond", 
	            "rocket", 
	            "sam",
	            "smoothness", 
	            "south-street", 
	            "start", 
	            "sunny", 
	            "swanky-purse",
	            "trontastic", 
	            "ui-darkness", 
	            "ui-lightness", 
	            "vader"
		};
		
		System.out.println("Fim do método getTemas()");
		return temas;
	}
	
}

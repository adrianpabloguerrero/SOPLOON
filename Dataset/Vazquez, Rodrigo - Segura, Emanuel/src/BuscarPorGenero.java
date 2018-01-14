
public class BuscarPorGenero extends Condicion {
	
	private String valor;
	
	public BuscarPorGenero(String valor){
		this.valor = valor;
	}
	
	public boolean cumple(ElementoMusical elemento) {
		return ((Pista)elemento).getGenero().toLowerCase().contains(valor.toLowerCase());
	}
}

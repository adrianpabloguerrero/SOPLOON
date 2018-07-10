
public class BuscarPorArtista extends Condicion {
	
	private String valor;
	
	public BuscarPorArtista(String valor){
		this.valor = valor;
	}
	
	public boolean cumple(ElementoMusical elemento) {
		return ((Pista)elemento).getArtista().toLowerCase().contains(valor.toLowerCase());
	}
}

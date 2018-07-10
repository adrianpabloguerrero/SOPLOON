
public class BuscarPorAlbum extends Condicion {
	
	private String valor;
	
	public BuscarPorAlbum(String valor){
		this.valor = valor;
	}
	
	public boolean cumple(ElementoMusical elemento) {
		return ((Pista)elemento).getAlbum().toLowerCase().contains(valor.toLowerCase());
	}
}


public class TituloIgual extends Condicion {

	String dato;

	public TituloIgual(String dato) {
		this.dato = dato.toLowerCase();
	}

	public boolean cumple(Pista p) {
		return p.getTituloAlbum().toLowerCase().contains(this.dato);
	}
}


public class GeneroIgual extends Condicion{

	String dato;

	public GeneroIgual(String dato) {
		this.dato = dato.toLowerCase();
	}

	public boolean cumple(Pista p) {
		return p.getGenero().toLowerCase().contains(this.dato);
	}
}

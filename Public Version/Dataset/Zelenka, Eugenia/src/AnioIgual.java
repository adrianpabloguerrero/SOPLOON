
public class AnioIgual extends Condicion {

	int dato;

	public AnioIgual(int dato) {
		this.dato = dato;
	}

	public boolean cumple(Pista p) {
		return p.getAnio() == dato;
	}
}

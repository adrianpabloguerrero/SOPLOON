
public class AnioMayor extends Condicion {

	int dato;

	public AnioMayor(int dato) {
		this.dato = dato;
	}

	public boolean cumple(Pista p) {
		return p.getAnio() > dato;
	}
}

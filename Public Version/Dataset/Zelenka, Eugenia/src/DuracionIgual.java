
public class DuracionIgual extends Condicion {
	
	int dato;
	
	public DuracionIgual(int dato) {
		this.dato = dato;
	}

	public boolean cumple(Pista p) {
		return p.getDuracion() == dato;
	}
}

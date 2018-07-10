
public class InterpreteIgual extends Condicion{

	String dato;
	
	public InterpreteIgual(String dato) {
		this.dato = dato.toLowerCase();
	}
	
	public boolean cumple(Pista p) {
		return p.getInterprete().toLowerCase().contains(this.dato);
	}
}

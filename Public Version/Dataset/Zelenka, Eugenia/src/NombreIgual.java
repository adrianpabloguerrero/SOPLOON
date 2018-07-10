
public class NombreIgual extends Condicion {

	String dato;
	
	public NombreIgual(String dato) {
		this.dato = dato.toLowerCase();
	}
	
	public boolean cumple(Pista p){
		return p.getNombre().toLowerCase().contains(dato);
	}

}

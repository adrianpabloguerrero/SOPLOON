package sistema;

import java.util.Vector;

public abstract class ElementoMusical {
	String nombre;

	public ElementoMusical(String nomb) {
		nombre = nomb;
	}
	public String getNombre() {
		return nombre;
	}
	
	public abstract int getDuracion();
	public abstract int getCantidad();
	public abstract String getResumen();
	public abstract Vector<Pista> buscador(Busqueda b);

	public abstract Pista eliminarPista(Pista em);

}

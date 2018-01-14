package sistema;

public class BuscarNombre extends Busqueda {
	
	String nombre;

	public BuscarNombre(String _nombre) {
		nombre = _nombre;
	}

	public boolean busqueda(Pista p) {
		return coincide(p.getNombre(), nombre);
	}


}

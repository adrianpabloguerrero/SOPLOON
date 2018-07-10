package sistema;

public class BuscarAlbum extends Busqueda {

	String nombre;

	public BuscarAlbum(String _nombre) {
		nombre = _nombre;
	}

	public boolean busqueda(Pista p) {
		return coincide(p.getAlbum(), nombre);
	}

}

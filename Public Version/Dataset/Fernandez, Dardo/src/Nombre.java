public class Nombre extends Condicion {

	private String nombreBusqueda;

	public Nombre (String nombreBusqueda) {
		this.nombreBusqueda = nombreBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return pista.getNombre().toLowerCase().contains(nombreBusqueda.toLowerCase());
	}

}

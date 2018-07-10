public class PorNombre extends Condicion {

	private String nombreBusqueda;

	public PorNombre (String nombreBusqueda) {
		this.nombreBusqueda = nombreBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return pista.getNombre().toLowerCase().contains(nombreBusqueda.toLowerCase());
	}

}
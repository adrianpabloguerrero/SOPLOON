public class DuracionMenor extends Condicion {

	private int duracionBusqueda;

	public DuracionMenor (int duracionBusqueda) {
		this.duracionBusqueda = duracionBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return duracionBusqueda > pista.getDuracion();
	}

}

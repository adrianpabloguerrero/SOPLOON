public class PorDuracionMenor extends Condicion {

	private int duracionBusqueda;

	public PorDuracionMenor (int duracionBusqueda) {
		this.duracionBusqueda = duracionBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return duracionBusqueda > pista.getDuracion();
	}

}
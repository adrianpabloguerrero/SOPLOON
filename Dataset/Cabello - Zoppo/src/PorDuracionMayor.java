public class PorDuracionMayor extends Condicion {

	private int duracionBusqueda;

	public PorDuracionMayor (int duracionBusqueda) {
		this.duracionBusqueda = duracionBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return duracionBusqueda < pista.getDuracion();
	}

}
public class DuracionMayor extends Condicion {

	private int duracionBusqueda;

	public DuracionMayor (int duracionBusqueda) {
		this.duracionBusqueda = duracionBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return duracionBusqueda < pista.getDuracion();
	}

}

public class PorDuracionIgual extends Condicion {

	private int duracionBusqueda;

	public PorDuracionIgual (int duracionBusqueda) {
		this.duracionBusqueda = duracionBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return (duracionBusqueda == pista.getDuracion());
	}
}
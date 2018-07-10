public class DuracionIgual extends Condicion {

	private int duracionBusqueda;

	public DuracionIgual (int duracionBusqueda) {
		this.duracionBusqueda = duracionBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return (duracionBusqueda == pista.getDuracion());
	}
}

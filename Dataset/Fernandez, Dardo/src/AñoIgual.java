public class AñoIgual extends Condicion {

	private int añoBusqueda;

	public AñoIgual (int añoBusqueda) {
		this.añoBusqueda = añoBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		boolean resultado = (añoBusqueda == pista.getAño());
		return resultado;
	}

}

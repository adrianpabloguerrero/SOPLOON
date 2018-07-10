public class PorAñoIgual extends Condicion {
	
	private int añoBusqueda;

	public PorAñoIgual (int añoBusqueda) {
		this.añoBusqueda = añoBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		boolean resultado = (añoBusqueda == pista.getAño());
		return resultado;
	}

}
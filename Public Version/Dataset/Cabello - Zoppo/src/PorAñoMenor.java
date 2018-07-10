public class PorAñoMenor extends Condicion {
	
	private int añoBusqueda;

	public PorAñoMenor (int añoBusqueda) {
		this.añoBusqueda = añoBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return (añoBusqueda > pista.getAño());

	}

}
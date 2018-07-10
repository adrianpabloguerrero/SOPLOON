public class PorAñoMayor extends Condicion {
	
	private int añoBusqueda;

	public PorAñoMayor (int añoBusqueda) {
		this.añoBusqueda = añoBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return (añoBusqueda < pista.getAño());
	}

}
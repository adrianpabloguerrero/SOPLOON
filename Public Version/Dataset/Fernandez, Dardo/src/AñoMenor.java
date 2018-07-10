public class AñoMenor extends Condicion {

	private int añoBusqueda;

	public AñoMenor (int añoBusqueda) {
		this.añoBusqueda = añoBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return (añoBusqueda > pista.getAño());

	}

}

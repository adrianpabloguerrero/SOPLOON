public class AñoMayor extends Condicion {

	private int añoBusqueda;

	public AñoMayor (int añoBusqueda) {
		this.añoBusqueda = añoBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return (añoBusqueda < pista.getAño());
	}

}

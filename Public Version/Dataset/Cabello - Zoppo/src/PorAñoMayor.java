public class PorA�oMayor extends Condicion {
	
	private int a�oBusqueda;

	public PorA�oMayor (int a�oBusqueda) {
		this.a�oBusqueda = a�oBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return (a�oBusqueda < pista.getA�o());
	}

}
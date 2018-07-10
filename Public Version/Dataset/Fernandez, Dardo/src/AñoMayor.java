public class A�oMayor extends Condicion {

	private int a�oBusqueda;

	public A�oMayor (int a�oBusqueda) {
		this.a�oBusqueda = a�oBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return (a�oBusqueda < pista.getA�o());
	}

}

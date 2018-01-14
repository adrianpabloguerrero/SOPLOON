public class A�oMenor extends Condicion {

	private int a�oBusqueda;

	public A�oMenor (int a�oBusqueda) {
		this.a�oBusqueda = a�oBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return (a�oBusqueda > pista.getA�o());

	}

}

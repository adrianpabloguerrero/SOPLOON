public class A�oIgual extends Condicion {

	private int a�oBusqueda;

	public A�oIgual (int a�oBusqueda) {
		this.a�oBusqueda = a�oBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		boolean resultado = (a�oBusqueda == pista.getA�o());
		return resultado;
	}

}

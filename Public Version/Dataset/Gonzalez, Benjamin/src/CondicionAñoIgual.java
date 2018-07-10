package Lista_de_Musica;


public class CondicionA�oIgual extends Condicion {
	private int valor;
	public CondicionA�oIgual(int valor) {
		this.valor = valor;
	}

	@Override
	public boolean cumple(Elemento e) {
		return valor == ((Pista) e).getA�o();
	}

}

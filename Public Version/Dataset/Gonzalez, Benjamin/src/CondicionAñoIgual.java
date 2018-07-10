package Lista_de_Musica;


public class CondicionAñoIgual extends Condicion {
	private int valor;
	public CondicionAñoIgual(int valor) {
		this.valor = valor;
	}

	@Override
	public boolean cumple(Elemento e) {
		return valor == ((Pista) e).getAño();
	}

}

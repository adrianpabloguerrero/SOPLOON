package Lista_de_Musica;


public class CondicionAñoMenor extends Condicion {
	private int valor;
	public CondicionAñoMenor(int valor) {
		this.valor = valor;
	}

	@Override
	public boolean cumple(Elemento e) {
		return valor > ((Pista) e).getAño();
	}

}

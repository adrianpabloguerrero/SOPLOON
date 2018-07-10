package Lista_de_Musica;


public class CondicionAņoMenor extends Condicion {
	private int valor;
	public CondicionAņoMenor(int valor) {
		this.valor = valor;
	}

	@Override
	public boolean cumple(Elemento e) {
		return valor > ((Pista) e).getAņo();
	}

}

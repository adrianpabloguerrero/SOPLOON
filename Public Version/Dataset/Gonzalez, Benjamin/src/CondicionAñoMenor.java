package Lista_de_Musica;


public class CondicionA�oMenor extends Condicion {
	private int valor;
	public CondicionA�oMenor(int valor) {
		this.valor = valor;
	}

	@Override
	public boolean cumple(Elemento e) {
		return valor > ((Pista) e).getA�o();
	}

}

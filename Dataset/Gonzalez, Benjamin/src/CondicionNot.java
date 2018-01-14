package Lista_de_Musica;

public class CondicionNot extends Condicion {
	private Condicion negada;
	public CondicionNot(Condicion negada) {
		this.negada = negada;
	}

	@Override
	public boolean cumple(Elemento e) {
		return !(negada.cumple(e));
	}

}

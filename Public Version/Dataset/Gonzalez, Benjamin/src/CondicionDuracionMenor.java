package Lista_de_Musica;

public class CondicionDuracionMenor extends Condicion {
	public int valor;
	public CondicionDuracionMenor(int valor) {
		this.valor = valor;
	}

	@Override
	public boolean cumple(Elemento e) {
		return e.getDuracion()<valor;
	}

}

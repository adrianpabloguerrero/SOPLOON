package Lista_de_Musica;

public class CondicionDuracionIgual extends Condicion {
	private int valor;
	
	public CondicionDuracionIgual(int valor) {
		this.valor = valor;
	}

	@Override
	public boolean cumple(Elemento e) {
		return e.getDuracion() == valor;
	}

}

package Lista_de_Musica;

public class CondicionGenero extends Condicion {
	private String valor;
	public CondicionGenero(String valor) {
		this.valor = valor;
	}

	@Override
	public boolean cumple(Elemento e) {
		return e.getGenero().toLowerCase().contains(valor);
	}

}

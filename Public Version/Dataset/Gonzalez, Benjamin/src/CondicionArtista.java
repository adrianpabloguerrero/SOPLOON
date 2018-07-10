package Lista_de_Musica;

public class CondicionArtista extends Condicion {
	private String valor;
	
	public CondicionArtista(String valor) {
		this.valor = valor;
	}

	@Override
	public boolean cumple(Elemento e) {
		return ((Pista)e).getArtista().toLowerCase().contains(valor);
	}

}

package Lista_de_Musica;

public class CondicionAlbum extends Condicion {
	private String valor;
	public CondicionAlbum(String valor) {
		this.valor = valor;
	}

	@Override
	public boolean cumple(Elemento e) {
		return e.getTituloAlbum().toLowerCase().contains(valor.toLowerCase());
		}

}

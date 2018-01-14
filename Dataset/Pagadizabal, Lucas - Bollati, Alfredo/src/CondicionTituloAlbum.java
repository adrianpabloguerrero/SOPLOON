
public class CondicionTituloAlbum extends Condicion {

	private String tituloAlbum;

	public CondicionTituloAlbum(String tA){
		tituloAlbum = tA.toLowerCase();
	}

	@Override
	public boolean esIgual(PLFE e) {
		return ((Pista)e).getTituloAlbum().toLowerCase().contains(tituloAlbum);
	}


}
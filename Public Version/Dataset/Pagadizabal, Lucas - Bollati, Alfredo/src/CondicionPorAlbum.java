
public class CondicionPorAlbum extends Condicion {
	
	private	String album;
	
	public CondicionPorAlbum(String s){
		album = s.toLowerCase();
	}
	
	@Override
	public boolean esIgual(PLFE e) {
		return ((Pista)e).getTituloAlbum().toLowerCase().contains(album);
	}

}

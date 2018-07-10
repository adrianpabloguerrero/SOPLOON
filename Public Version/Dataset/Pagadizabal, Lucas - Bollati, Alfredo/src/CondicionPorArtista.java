
public class CondicionPorArtista extends Condicion {
	
	private String artista;
	
	public CondicionPorArtista(String s){	
		artista = s.toLowerCase();
	}
	
	@Override
	public boolean esIgual(PLFE e) {
		return ((Pista)e).getArtista().toLowerCase().contains(artista);
	}
	
}

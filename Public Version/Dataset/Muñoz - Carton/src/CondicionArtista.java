package practicoEspecial;

public class CondicionArtista extends Condicion {
	private String artista;
	
	public CondicionArtista(String a){
		artista=a.toLowerCase();
	}

	public boolean cumple(Pista p) {
		return (p.getArtista().toLowerCase().contains(artista));

	}
	
}

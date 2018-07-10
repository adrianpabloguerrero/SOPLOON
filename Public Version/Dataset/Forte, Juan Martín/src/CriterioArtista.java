public class CriterioArtista extends Criterio {
	private String artista;

	public CriterioArtista(String artista){
		this.artista = artista.toLowerCase();
	}
	public boolean cumple(Cancion c) {

		if(c.getArtista().toLowerCase().contains(artista)){
			return true;	
		}
		else return false;
	}
}
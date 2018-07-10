public class CriterioAlbum extends Criterio {
	private String album;

	public CriterioAlbum(String artista){
		this.album = album.toLowerCase(); 
	}
	public boolean cumple(Cancion c) {

		if(c.getAlbum().toLowerCase().contains(album)){ 
			return true;	
		}
		else return false;
	}
}
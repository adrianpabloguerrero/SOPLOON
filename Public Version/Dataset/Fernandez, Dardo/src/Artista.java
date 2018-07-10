public class Artista extends Condicion {

	private String artistaBusqueda;

	public Artista (String artistaBusqueda) {
		this.artistaBusqueda = artistaBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return pista.getArtista().toLowerCase().contains(artistaBusqueda.toLowerCase());
	}

}

public class PorArtista extends Condicion {

	private String artistaBusqueda;

	public PorArtista (String artistaBusqueda) {
		this.artistaBusqueda = artistaBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return pista.getArtista().toLowerCase().contains(artistaBusqueda.toLowerCase());
	}

}
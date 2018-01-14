public class PorAlbum extends Condicion {

	private String albumBusqueda;

	public PorAlbum (String albumBusqueda) {
		this.albumBusqueda = albumBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return pista.getAlbum().toLowerCase().contains(albumBusqueda.toLowerCase());
	}

}
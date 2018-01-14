public class Album extends Condicion {

	private String albumBusqueda;

	public Album (String albumBusqueda) {
		this.albumBusqueda = albumBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return pista.getAlbum().toLowerCase().contains(albumBusqueda.toLowerCase());
	}

}

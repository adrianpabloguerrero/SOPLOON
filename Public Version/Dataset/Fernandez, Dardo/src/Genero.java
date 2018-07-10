public class Genero extends Condicion {

	private String generoBusqueda;

	public Genero (String generoBusqueda) {
		this.generoBusqueda = generoBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return pista.getGenero().toLowerCase().contains(generoBusqueda.toLowerCase());
	}

}

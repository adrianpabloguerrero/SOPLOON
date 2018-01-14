public class PorGenero extends Condicion {

	private String generoBusqueda;

	public PorGenero (String generoBusqueda) {
		this.generoBusqueda = generoBusqueda;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return pista.getGenero().toLowerCase().contains(generoBusqueda.toLowerCase());
	}
 
}
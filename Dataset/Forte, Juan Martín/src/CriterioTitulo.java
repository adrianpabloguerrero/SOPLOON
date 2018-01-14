public class CriterioTitulo extends Criterio {
	
	private String titulo;
	
	public CriterioTitulo (String titulo){
		this.titulo = titulo.toLowerCase();
	}
	public boolean cumple(Cancion c1) {
		return c1.getTitulo().toLowerCase().contains(titulo);
	}
}
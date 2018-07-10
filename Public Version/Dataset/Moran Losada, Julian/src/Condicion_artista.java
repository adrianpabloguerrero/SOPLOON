package PracticoEspecial;

public class Condicion_artista extends Condicion {
	protected String filtro;
	
	public Condicion_artista(String _filtro){
		filtro = _filtro;
	}
	
	public boolean cumple(Pista p){
		return p.getArtista().toUpperCase().contains(filtro.toUpperCase());
	}
}

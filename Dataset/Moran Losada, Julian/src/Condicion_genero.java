package PracticoEspecial;

public class Condicion_genero extends Condicion {
	protected String filtro;
	
	public Condicion_genero(String _filtro){
		filtro = _filtro;
	}
	
	
	public boolean cumple(Pista p){
		return (p.getGenero().toUpperCase()).contains(filtro.toUpperCase());
	}
}

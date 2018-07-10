package PracticoEspecial;

public class Condicion_nombre extends Condicion {
	protected String filtro;
	
	public Condicion_nombre(String _filtro){
		filtro = _filtro;
	}
	
	public boolean cumple(Pista p){
		return p.getTitulo().toUpperCase().contains(filtro.toUpperCase());
	}
}

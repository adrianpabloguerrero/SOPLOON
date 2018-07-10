package PracticoEspecial;

public class Condicion_negacion extends Condicion {
	protected Condicion filtro;
	
	public Condicion_negacion(Condicion _filtro){
		filtro=_filtro;
	}
	
	public boolean cumple(Pista p){
		return !filtro.cumple(p);
	}
}

package PracticoEspecial;

public class Condicion_anio extends Condicion {
	protected int filtro;
	
	public Condicion_anio(int _filtro){
		filtro = _filtro;
	}
	
	public boolean cumple(Pista p){
		return p.getAnio()>=filtro;
	}
}

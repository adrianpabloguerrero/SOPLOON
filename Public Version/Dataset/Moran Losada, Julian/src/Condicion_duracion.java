package PracticoEspecial;

public class Condicion_duracion extends Condicion {
	protected int filtro;
	
	public Condicion_duracion(int _filtro){
		filtro = _filtro;
	}
	
	public boolean cumple(Pista p){
		return p.getDuracion()>=filtro;
	}
}

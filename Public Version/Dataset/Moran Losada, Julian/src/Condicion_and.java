package PracticoEspecial;

public class Condicion_and extends Condicion {
	protected Condicion c1;
	protected Condicion c2;
	
	public Condicion_and(Condicion _c1, Condicion _c2){
		 c1 = _c1;
		 c2 = _c2;
	}
	
	public boolean cumple(Pista p){
		return c1.cumple(p)&&c2.cumple(p);
	}
}

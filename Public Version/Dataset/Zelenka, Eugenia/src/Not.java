
public class Not extends Condicion {

	Condicion c1;
	
	public Not(Condicion c1) {
		this.c1 = c1;
	}
	
	public boolean cumple(Pista p) {
		return (!c1.cumple(p));
	}
}
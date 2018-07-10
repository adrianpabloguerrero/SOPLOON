package trabajoEspecialProg2;

public class CondicionOr extends Condicion {

	Condicion c1;
	Condicion c2;
	
	public CondicionOr(Condicion c1, Condicion c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	public boolean cumple(Pista p) {
		return (c1.cumple(p) || c2.cumple(p));
	}
}

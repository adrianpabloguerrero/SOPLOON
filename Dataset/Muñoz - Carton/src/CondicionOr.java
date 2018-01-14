package practicoEspecial;

public class CondicionOr extends Condicion{
	Condicion c1;
	Condicion c2;
	
	public CondicionOr(Condicion _c1, Condicion _c2) {
		c1=_c1;
		c2=_c2;
	}

	public boolean cumple(Pista p) {
		return (c1.cumple(p) || c2.cumple(p));
			 
	}
	

}

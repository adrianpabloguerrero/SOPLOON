
public class CondicionOr extends Condicion {
	
	Condicion c1;
	Condicion c2;
	
	public CondicionOr (Condicion a, Condicion b){
		c1=a;
		c2=b;
	}

	@Override
	public boolean esIgual(PLFE e) {
		return (c1.esIgual(e)) || (c2.esIgual(e));
	}
}

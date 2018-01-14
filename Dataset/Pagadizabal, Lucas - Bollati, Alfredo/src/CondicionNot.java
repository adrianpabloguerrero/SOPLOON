
public class CondicionNot extends Condicion{
	
	Condicion c1;
	
	public CondicionNot(Condicion a){
		c1=a;
	}

	@Override
	public boolean esIgual(PLFE e) {
		return !c1.esIgual(e);
	}
}

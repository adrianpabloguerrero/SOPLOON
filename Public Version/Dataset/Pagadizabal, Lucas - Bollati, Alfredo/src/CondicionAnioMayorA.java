
public class CondicionAnioMayorA extends Condicion{

	private int anio;
	
	public CondicionAnioMayorA(int a){
		anio = a;
	}

	@Override
	public boolean esIgual(PLFE e) {
		return anio < ((Pista)e).getAnio();
	}
	
	
}

public class CondicionDuracionMayorA extends Condicion{
	
	private int duracion;
	
	public CondicionDuracionMayorA(int d){
		duracion = d;
	}
	
	@Override
	public boolean esIgual(PLFE e) {
		return duracion < ((Pista)e).getDuracion();
	}

}


public class CondicionMayorYear extends Condicion{
	private int valor;
	
	public CondicionMayorYear (int _valor) {
		valor = _valor;
	}

	@Override
	public boolean cumple(ElementoAudio elemento) {
		return ((PistaAudio)elemento).getYear() > valor;
	}

}

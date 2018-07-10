
public class CondicionYear extends Condicion{
	private int valor;
	
	public CondicionYear (int _valor) {
		valor = _valor;
	}

	@Override
	public boolean cumple(ElementoAudio elemento) {
		return ((PistaAudio)elemento).getYear() == valor;
	}

}

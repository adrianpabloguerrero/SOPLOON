
public class CondicionDuracion extends Condicion{
	private int valor;
	
	public CondicionDuracion (int _valor) {
		valor = _valor;
	}

	@Override
	public boolean cumple(ElementoAudio elemento) {
		return elemento.getDuracion() == valor;
	}

}



public class CondicionMayorDuracion extends Condicion{
	private int valor;
	
	public CondicionMayorDuracion (int _valor) {
		valor = _valor;
	}

	@Override
	public boolean cumple(ElementoAudio elemento) {
		return elemento.getDuracion() > valor;
	}

}
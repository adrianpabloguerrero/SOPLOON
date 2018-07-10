
public class CondicionNot extends Condicion {
	private Condicion cond1;
	
	public CondicionNot (Condicion _cond1) {
		cond1 = _cond1;
	}

	@Override
	public boolean cumple(ElementoAudio elemento) {
		return !cond1.cumple(elemento);
	}

}
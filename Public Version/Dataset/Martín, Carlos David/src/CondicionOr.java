
public class CondicionOr extends Condicion {
	private Condicion cond1;
	private Condicion cond2;
	
	public CondicionOr (Condicion _cond1,Condicion _cond2) {
		cond1 = _cond1;
		cond2 = _cond2;
	}

	@Override
	public boolean cumple(ElementoAudio elemento) {
		return cond1.cumple(elemento)||cond2.cumple(elemento);
	}

}

public class CondicionGenero extends Condicion{
	private String valor;
	
	public CondicionGenero (String _valor) {
		valor = _valor;
	}

	@Override
	public boolean cumple(ElementoAudio elemento) {
		return ((PistaAudio)elemento).getGenero().toLowerCase().contains(valor.toLowerCase());
	}

}

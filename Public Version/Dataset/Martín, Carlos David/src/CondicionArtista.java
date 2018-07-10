
public class CondicionArtista extends Condicion{
	private String valor;
	
	public CondicionArtista (String _valor) {
		valor = _valor;
	}

	@Override
	public boolean cumple(ElementoAudio elemento) {
		return ((PistaAudio)elemento).getArtista().toLowerCase().contains(valor.toLowerCase());
	}

}

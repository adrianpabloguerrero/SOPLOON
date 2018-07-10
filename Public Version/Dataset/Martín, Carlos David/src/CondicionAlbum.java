
public class CondicionAlbum extends Condicion{
	private String valor;
	
	public CondicionAlbum (String _valor) {
		valor = _valor;
	}

	@Override
	public boolean cumple(ElementoAudio elemento) {
		return ((PistaAudio)elemento).getAlbum().toLowerCase().contains(valor.toLowerCase());
	}

}

public class CondicionNombre extends Condicion{
	private String valor;
	
	public CondicionNombre (String _valor) {
		valor = _valor;
	}

	@Override
	public boolean cumple(ElementoAudio elemento) {
		return elemento.getTitulo().toLowerCase().contains(valor.toLowerCase());	
		}

}

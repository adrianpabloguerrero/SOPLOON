package Lista_de_Musica;

public class CondicionNombre extends Condicion {
	private String valor;
	
	public CondicionNombre(String valor) {
		this.valor = valor;
	}

	@Override
	public boolean cumple(Elemento e) {
		return e.getNombre().toLowerCase().contains(valor);
	}	
;
}

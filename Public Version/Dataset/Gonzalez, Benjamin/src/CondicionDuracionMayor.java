package Lista_de_Musica;

public class CondicionDuracionMayor extends Condicion {
	private int valor;
	
	public CondicionDuracionMayor(int valor) {
		this.valor = valor;
		
	}

	@Override
	public boolean cumple(Elemento e) {
		return e.getDuracion()>valor;
	}

}

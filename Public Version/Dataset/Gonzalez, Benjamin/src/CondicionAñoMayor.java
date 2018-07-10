package Lista_de_Musica;


public class CondicionAñoMayor extends Condicion {
	private int valor;
	public CondicionAñoMayor(int valor) {
		this.valor = valor;
	}

	@Override
	public boolean cumple(Elemento e) {
		return valor < ((Pista) e).getAño();
	}

}
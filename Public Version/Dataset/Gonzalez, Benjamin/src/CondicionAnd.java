package Lista_de_Musica;

public class CondicionAnd extends Condicion {
	private Condicion derecha;
	private Condicion izquierda;
	
	public CondicionAnd(Condicion derecha, Condicion izquierda) {
		this.derecha = derecha;
		this.izquierda = izquierda;
		
	}

	@Override
	public boolean cumple(Elemento e) {
		return (derecha.cumple(e) && izquierda.cumple(e));
	}

	public void setDerecha(Condicion derecha) {
		this.derecha = derecha;
	}

	public void setIzquierda(Condicion izquierda) {
		this.izquierda = izquierda;
	}

}

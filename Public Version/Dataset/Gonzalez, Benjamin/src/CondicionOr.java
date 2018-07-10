package Lista_de_Musica;

public class CondicionOr extends Condicion {
	private Condicion derecha;
	private Condicion izquierda;
	
	public CondicionOr(Condicion derecha,Condicion izquierda) {
		this.derecha = derecha; 
		this.izquierda =izquierda;
	}

	@Override
	public boolean cumple(Elemento e) {
		// TODO Auto-generated method stub
		return ((derecha.cumple(e))||(izquierda.cumple(e)));
	}

}

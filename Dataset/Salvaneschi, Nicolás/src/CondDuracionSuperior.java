
public class CondDuracionSuperior extends Condicion {
	private int valor;
	
	public CondDuracionSuperior(int val){
		valor=val;
	}
	
	public int getValor(){
		return valor;
	}
	
	public boolean cumple(Elemento e){
		return(((Cancion)e).getDuracion()>valor);		
	}

}

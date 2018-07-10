
public class CondFechaMayor extends Condicion {
	private int a�o;
	
	public CondFechaMayor (int fecha){
		a�o=fecha;
	}
	
	public int getFecha(){
		return a�o;
	}
	
	public boolean cumple(Elemento e){
		return ((Cancion)e).getA�o()>=a�o;
	}
	
	

}

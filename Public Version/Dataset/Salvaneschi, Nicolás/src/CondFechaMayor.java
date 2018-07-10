
public class CondFechaMayor extends Condicion {
	private int año;
	
	public CondFechaMayor (int fecha){
		año=fecha;
	}
	
	public int getFecha(){
		return año;
	}
	
	public boolean cumple(Elemento e){
		return ((Cancion)e).getAño()>=año;
	}
	
	

}

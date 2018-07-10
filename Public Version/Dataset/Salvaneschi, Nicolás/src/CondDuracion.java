
public class CondDuracion extends Condicion {
	
	private int duracion;
	
	public CondDuracion(int dura){
		duracion=dura;		
	}
	
	public int getDuracion(){
		return duracion;
	}
	
	public boolean cumple(Elemento e){
		return (((Cancion)e).getDuracion()==duracion);		 
			
	}
	

}

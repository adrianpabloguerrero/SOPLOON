
public class And extends Condicion {
	private Condicion uno;
	private Condicion dos;
	
	public And(Condicion c1,Condicion c2){
		uno=c1;
		dos=c2;
	}
	
	public Condicion getCondicionUno(){
		return uno;
	}
	
	public Condicion getCondicionDos(){
		return dos;
	}
	
	public boolean cumple(Elemento e){
		return uno.cumple(e)&&dos.cumple(e);
	}
	

}

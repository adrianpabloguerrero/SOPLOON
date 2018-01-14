
public class Not extends Condicion {
	private Condicion c;
	
	public Not(Condicion condi){
		c=condi;
	}
	
	public Condicion getNot(){
		return c;
	}
	
	public boolean cumple(Elemento e){
		return !c.cumple(e);
	}

}

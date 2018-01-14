package trabajoPracticoEspecial;

public class CondicionNot extends Condicion{

	private Condicion c;
	
	public CondicionNot(Condicion c){
		
		this.c = c;
		
	}
	
	@Override
	public boolean cumple(Pista p) {
		
		return (!c.cumple(p));
		
	}
	
	

}

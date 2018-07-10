package trabajoPracticoEspecial;

public class CondicionAñoMayorQue extends Condicion{
	
	private int input;
	
	public CondicionAñoMayorQue(int input){
		
		this.input = input;
		
	}

	public boolean cumple(Pista p) {
		
		return p.getAnio() > input;
		
	}
	
}

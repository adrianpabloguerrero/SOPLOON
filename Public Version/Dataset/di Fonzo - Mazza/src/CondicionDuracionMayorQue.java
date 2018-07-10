package trabajoPracticoEspecial;

public class CondicionDuracionMayorQue extends Condicion{
	
	private int input;
	
	public CondicionDuracionMayorQue(int input){
		
		this.input = input;
		
	}

	public boolean cumple(Pista p) {
		
		return p.getDuracion() > input;
		
	}

}

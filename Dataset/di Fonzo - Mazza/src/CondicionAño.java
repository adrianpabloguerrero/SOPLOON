package trabajoPracticoEspecial;

public class CondicionAño extends Condicion{

	private Integer input;
	
	public CondicionAño(Integer input){
		
		this.input = input;
		
	}

	
	public boolean cumple(Pista p) {
		
		return p.getAnio() == input;
	}
	
	

}

package trabajoPracticoEspecial;

public class CondicionInterprete extends Condicion{
	
	private String input;
	
	public CondicionInterprete(String input){
		
		this.input = input.toLowerCase();
		
	}
		
	public boolean cumple(Pista p) {
		
		return (p.getInterprete().toLowerCase().contains(input));
			
	}

}

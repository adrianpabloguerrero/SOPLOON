package trabajoPracticoEspecial;

public class CondicionGenero extends Condicion{
	
	private String input;
	
	public CondicionGenero(String input){
		
		this.input = input.toLowerCase();
		
	}
		
	public boolean cumple(Pista p) {
		
		return (p.getGenero().toLowerCase().contains(input));
			
	}

}

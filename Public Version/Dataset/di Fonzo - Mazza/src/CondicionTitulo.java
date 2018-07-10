package trabajoPracticoEspecial;

public class CondicionTitulo extends Condicion{

	private String input;
	
	public CondicionTitulo(String input){
		
		this.input = input.toLowerCase();
		
	}
		
	public boolean cumple(Pista p) {
		
		return (p.getTitulo().toLowerCase().contains(input));
			
	}
		
}

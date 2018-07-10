package trabajoPracticoEspecial;

public class CondicionAņo extends Condicion{

	private Integer input;
	
	public CondicionAņo(Integer input){
		
		this.input = input;
		
	}

	
	public boolean cumple(Pista p) {
		
		return p.getAnio() == input;
	}
	
	

}

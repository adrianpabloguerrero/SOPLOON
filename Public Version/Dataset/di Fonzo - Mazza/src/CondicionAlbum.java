	package trabajoPracticoEspecial;

public class CondicionAlbum extends Condicion{
	
	private String input;
	
	public CondicionAlbum(String input){
		
		this.input = input.toLowerCase();
		
	}
		
	public boolean cumple(Pista p) {
		
		return (p.getAlbum().toLowerCase().contains(input));
			
	}

}

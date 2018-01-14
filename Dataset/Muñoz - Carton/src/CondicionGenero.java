package practicoEspecial;

public class CondicionGenero extends Condicion{

	private String genero;
	
	public CondicionGenero(String g){
		genero=g.toLowerCase();
	}

	public boolean cumple(Pista p) {
		return (p.getGenero().toLowerCase().contains(genero));

	}
	
}

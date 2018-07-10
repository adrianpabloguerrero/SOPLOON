
public class CondicionPorGenero extends Condicion{
	
	private String genero;
	
	public CondicionPorGenero(String s){
		genero = s.toLowerCase();
	}
	
	@Override
	public boolean esIgual(PLFE e) {
		return ((Pista)e).getGenero().toLowerCase().contains(genero);
	}
	
	
}

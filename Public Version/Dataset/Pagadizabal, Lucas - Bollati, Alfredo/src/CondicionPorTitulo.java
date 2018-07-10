
public class CondicionPorTitulo extends Condicion {
	
	private String titulo;
	
	public CondicionPorTitulo(String s){
		titulo = s.toLowerCase();
	}
	
	@Override
	public boolean esIgual(PLFE e) {
		return ((Pista)e).getTitulo().toLowerCase().contains(titulo);
	}
	
	
}

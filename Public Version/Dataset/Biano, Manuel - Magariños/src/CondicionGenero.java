
	
public class CondicionGenero implements Condicion {
	private String valor;
	
	public CondicionGenero(String valor){
		this.valor = valor;
	}
	
	public boolean cumple(ElementoMusical e1){
		return ((Pista)e1).getGenero().toLowerCase().contains(valor.toLowerCase());
	}
}



public class CondicionNombre implements Condicion{
	private String valor;
	
	public CondicionNombre(String valor){
		this.valor = valor; 
	}
	
	public boolean cumple(ElementoMusical e1){
		return ((Pista)e1).getTitulo().toLowerCase().contains(valor.toLowerCase());
	}

}
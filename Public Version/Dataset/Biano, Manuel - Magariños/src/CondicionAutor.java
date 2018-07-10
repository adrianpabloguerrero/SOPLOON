

public class CondicionAutor implements Condicion{
	private String valor;
	
	public CondicionAutor(String valor){
		this.valor=valor;
	}
	
	public boolean cumple(ElementoMusical e1) {
		
		return ((Pista)e1).getAutor().toLowerCase().contains(valor.toLowerCase());
	}		
}

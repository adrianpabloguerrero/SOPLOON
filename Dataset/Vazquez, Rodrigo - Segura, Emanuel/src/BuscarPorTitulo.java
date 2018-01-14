
public class BuscarPorTitulo extends Condicion{

	private String valor;
	
	public BuscarPorTitulo(String valor){
		this.valor = valor;
	}
	
	public boolean cumple(ElementoMusical elemento) {
		return ((Pista)elemento).getTitulo().toLowerCase().contains(valor.toLowerCase());
	}
}


public class BuscarPorFecha extends Condicion{
	private int valor;
	
	public BuscarPorFecha(int valor){
		this.valor = valor;
	}
	
	public boolean cumple(ElementoMusical elemento) {
		return (((Pista)elemento).getAnio()== valor);
	}
}

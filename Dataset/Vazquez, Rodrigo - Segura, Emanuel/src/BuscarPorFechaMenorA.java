
public class BuscarPorFechaMenorA extends Condicion{
	private int valor;
	
	public BuscarPorFechaMenorA(int valor){
		this.valor = valor;
	}
	
	public boolean cumple(ElementoMusical elemento) {
		return (((Pista)elemento).getAnio() < valor);
	}
}

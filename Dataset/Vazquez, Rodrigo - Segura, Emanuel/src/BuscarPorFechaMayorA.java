
public class BuscarPorFechaMayorA extends Condicion{
	private int valor;
	
	public BuscarPorFechaMayorA(int valor){
		this.valor = valor;
	}
	
	public boolean cumple(ElementoMusical elemento) {
		return (((Pista)elemento).getAnio() > valor);
	}
}

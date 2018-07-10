
public class BuscarPorDuracionMayorA extends Condicion{
	
private int valor;
	
	public BuscarPorDuracionMayorA(int valor){
		this.valor = valor;
	}

	public boolean cumple(ElementoMusical elemento) {
		return (((Pista)elemento).getDuracion() > valor);
	}
}

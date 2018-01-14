
public class BuscarPorDuracionMenorA extends Condicion{
	
private int valor;
	
	public BuscarPorDuracionMenorA(int valor){
		this.valor = valor;
	}

	public boolean cumple(ElementoMusical elemento) {
		return (((Pista)elemento).getDuracion() < valor);
	}
}

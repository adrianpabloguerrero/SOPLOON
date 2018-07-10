
	
public class CondicionFechaMenorA implements Condicion{
	int valor;
	
	public CondicionFechaMenorA(int valor){
		this.valor = valor;
	}
	
	public boolean cumple(ElementoMusical e1){
		return ((Pista)e1).getAnio()<valor;
	}
}

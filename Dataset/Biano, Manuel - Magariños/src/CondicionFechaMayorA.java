
	
public class CondicionFechaMayorA implements Condicion{
	int valor;
	
	public CondicionFechaMayorA(int valor){
		this.valor = valor;
	}
	
	public boolean cumple(ElementoMusical e1){
		return ((Pista)e1).getAnio()>valor;
	}
}

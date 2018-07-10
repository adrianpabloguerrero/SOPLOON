
	
public class CondicionFechaIgual implements Condicion{
	int valor;
	
	public CondicionFechaIgual(int valor){
		this.valor = valor;
	}
	
	public boolean cumple(ElementoMusical e1){
		return ((Pista)e1).getAnio()==valor;
	}
}

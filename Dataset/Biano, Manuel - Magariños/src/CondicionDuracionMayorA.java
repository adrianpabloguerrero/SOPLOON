

public class CondicionDuracionMayorA implements Condicion {
	
	int valor;
	public CondicionDuracionMayorA(int valor){
		this.valor=valor;
	}
	
	public boolean cumple(ElementoMusical e1) {
		return ((Pista)e1).getDuracion()>valor;
	}
	
}

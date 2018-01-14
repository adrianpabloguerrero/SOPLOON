

public class CondicionDuracionMenorA implements Condicion {
	
	int valor;
	public CondicionDuracionMenorA(int valor){
		this.valor=valor;
	}
	
	public boolean cumple(ElementoMusical e1) {
		return ((Pista)e1).getDuracion()<valor;
	}
	
}

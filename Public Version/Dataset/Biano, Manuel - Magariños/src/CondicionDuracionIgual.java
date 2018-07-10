

public class CondicionDuracionIgual implements Condicion{
	int valor;
	
	public CondicionDuracionIgual(int valor){
		this.valor=valor;
	}
	
	public boolean cumple(ElementoMusical e1){
		return ((Pista)e1).getDuracion()==valor;
	}


}

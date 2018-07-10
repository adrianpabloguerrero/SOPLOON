
public class BuscarPorDuracion extends Condicion{
	
	private int valor;
	
	public BuscarPorDuracion(int valor){
		this.valor = valor;
	}
	
	public boolean cumple(ElementoMusical elemento) {
		return (((Pista)elemento).getDuracion()== valor);
	}
}

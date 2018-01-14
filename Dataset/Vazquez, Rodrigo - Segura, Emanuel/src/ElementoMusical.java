import java.util.Vector;

public abstract class ElementoMusical {
	
	public abstract String toString();
	
	public abstract int getCantElementos();
	
	public abstract Vector<ElementoMusical> devolverElemFiltrados(Condicion c1); 
	
	public abstract int getDuracion();
}
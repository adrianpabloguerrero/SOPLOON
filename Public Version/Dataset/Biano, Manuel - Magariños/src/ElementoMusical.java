
import java.util.Vector;


public abstract class ElementoMusical {
	
	public abstract int getDuracion();
	
	public abstract int cantidadElementos();
	
	public abstract String toString();
		
	public abstract Vector<ElementoMusical> coincide(Condicion c1); 
}
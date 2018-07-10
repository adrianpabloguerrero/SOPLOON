import java.util.Collections;
import java.util.Vector;
/**
 * Clase "compuesto" del composite, contiene un vector de Elementos de reproducción  
 * (pueden ser pistas, u otras listas)
 * 
 * @author Bernardo Corengia
 */
public class ListaReproduccion extends ElementoReproduccion{

	final Vector <ElementoReproduccion> elementos = new Vector<ElementoReproduccion>();
	
	
	public ListaReproduccion (String n) {
		super(n);
	}
	
	
	// ----------------------------- METODOS -------------------------------------
	
	public void agregarElemento(ElementoReproduccion e){
		elementos.add(e);
	}
	
	public void quitarElemento(ElementoReproduccion e){
		elementos.remove(e);
	}
	
	public void intercambiarElemento(ElementoReproduccion e1, ElementoReproduccion e2) {
		if (elementos.contains(e1) && elementos.contains(e2)) {
		Collections.swap(elementos,elementos.indexOf(e1),elementos.indexOf(e2));
		}
	}
	
	
	public int duracionTotal() {
		int duracion = 0;
		
		for (ElementoReproduccion elemento:elementos) {
			duracion += elemento.duracionTotal();
		}
		return duracion;
	}
	
	
	public int cantidadElementos() {
		int cantidad = 0;
		
		for (ElementoReproduccion elemento:elementos) {
			cantidad += elemento.cantidadElementos();
		}
		return cantidad;
	}

	
	public void imprimirColeccion() {
		System.out.println();
		System.out.println("Lista : "+getNombre());
		
		if (elementos.isEmpty()) {
			System.out.println("La lista está vacía");
		}
		for (ElementoReproduccion elemento:elementos) {
			elemento.imprimirColeccion();
		}
	}
}

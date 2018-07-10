
/**
 * Clase abstracta "componente" del composite. Permite tratar por igual listas y pistas.
 * 
 * @author Bernardo Corengia
 */
public abstract class ElementoReproduccion {
 String nombre;
 
 public ElementoReproduccion(String n) {
	 nombre = n;
 }
 
 public String getNombre() {
	 return nombre;
 }
 
 public void setNombre(String n) {
	 nombre = n;
 }
 /**
  *Devuelve la duración total de la colección.
  */
 public abstract int duracionTotal();
 /**
  *Devuelve la cantidad de pistas en la colección.
  */
 public abstract int cantidadElementos();
 /**
  *Imprime por pantalla el contenido de la colección.
  */
 public abstract void imprimirColeccion();

}






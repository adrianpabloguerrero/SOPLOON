import java.util.Vector;

public class Playlist extends ElementoMusical{
	private String nombre;
	private Vector<ElementoMusical> elementos; //Es de Clase elementoMusical para poder meter Pistas y Playlists dentro de una playlist
	
	public Playlist(String nombre){
		this.setNombre(nombre);
		elementos = new Vector<ElementoMusical>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void addElemento(ElementoMusical elemento){
		elementos.add(elemento);
	}
	
	public void removeElemento(ElementoMusical elemento){
		elementos.remove(elemento);
	}
	
	public int getDuracion(){
		int duracion = 0;
		for (int i = 0; i < elementos.size(); i++) {
			duracion += elementos.elementAt(i).getDuracion();
		}
		return duracion;
	}
	
	public String toString(){
		String canciones = "";
		for (int i = 0; i < elementos.size(); i++) {
			canciones += elementos.elementAt(i).toString();
		}
		return canciones;
	}
	
	public int getCantElementos(){
		int cantidad = 0;
		for (int i = 0; i < elementos.size(); i++) {
			cantidad += elementos.elementAt(i).getCantElementos();
		}
		return cantidad;
	}
	
	public void intercambiarPosicion(int pos1, int pos2){
		ElementoMusical aux;
		aux = elementos.elementAt(pos1);
		elementos.setElementAt(elementos.elementAt(pos2), pos1);
		elementos.setElementAt(aux, pos2);
	}
	
	public boolean contiene(ElementoMusical elemento){
		return elementos.contains(elemento);
	}
	
	public Vector<ElementoMusical> devolverElemFiltrados (Condicion condicion){
		Vector<ElementoMusical> elementosFiltrados = new Vector<ElementoMusical>();
		for (int i = 0; i < elementos.size(); i++) {
			ElementoMusical e1 = elementos.elementAt(i);
			Vector<ElementoMusical> vectorAux = e1.devolverElemFiltrados(condicion);
			elementosFiltrados.addAll(vectorAux);
		}
		return elementosFiltrados;
	}		
}
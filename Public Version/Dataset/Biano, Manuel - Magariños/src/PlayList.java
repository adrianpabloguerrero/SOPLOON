
import java.util.Collections;
import java.util.Vector;

import org.omg.CORBA.IRObject;

public class PlayList extends ElementoMusical {
	private String nombre;
	private Vector<ElementoMusical>elementos;
	
	public PlayList(String nombre) {
		this.nombre = nombre;
		this.elementos = new Vector<ElementoMusical>();
	}

	public int getDuracion() {
		int aux=0;
		for (int i = 0; i < elementos.size(); i++) {
			aux += elementos.elementAt(i).getDuracion();
		}
		return aux;
	}
	
	public int cantidadElementos(){
		int cantidadElementos=0;
		for (int i = 0; i < elementos.size(); i++) {
			cantidadElementos += elementos.elementAt(i).cantidadElementos();	
		}
		return cantidadElementos;
	}
	
	public String toString(){
		String auxImprimir="";
		for (int i = 0; i < elementos.size(); i++) {
			auxImprimir += elementos.elementAt(i).toString();
		}
		return auxImprimir;
	}
	
	public void agregarElemento(ElementoMusical e){
		elementos.add(e);
	}
	public void eliminarElemento(ElementoMusical e){
		elementos.remove(e);
		for (int i = 0; i < elementos.size(); i++) {
			eliminarElemento(e);		
		}
	}
	
	public void borrarElemento(ElementoMusical e1){
		elementos.remove(e1);
	}
	
	public void intercambiarPosiciones(int pos1, int pos2){
		Collections.swap(elementos, pos1, pos2);
	}
	
	public Vector<ElementoMusical> coincide(Condicion c1){
		Vector<ElementoMusical> elementosQueCumplen = new Vector<ElementoMusical>();
		for (int i = 0; i < elementos.size(); i++) {
//			if(c1.cumple(elementos.elementAt(i))){
//			elementosQueCumplen.addAll(elementos.elementAt(i).coincide(c1));
			ElementoMusical e1 = elementos.elementAt(i);
			Vector<ElementoMusical> vectorAux = e1.coincide(c1);
			elementosQueCumplen.addAll(vectorAux);
			}
		return elementosQueCumplen;
	}

}

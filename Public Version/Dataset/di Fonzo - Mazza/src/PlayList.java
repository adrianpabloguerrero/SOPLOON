package trabajoPracticoEspecial;

import java.util.Vector;

public class PlayList extends ElementoMusical{
	
	private String nombre;
	Vector<ElementoMusical> elementosMusicales;
	
	public PlayList(String nombre){
		
		elementosMusicales = new Vector <ElementoMusical>();
		this.nombre = nombre;
		
	}
	
	public void addElemento (ElementoMusical e){
		
		elementosMusicales.add(e);
		
	}
	public void eliminarElemento(ElementoMusical e){
		
		elementosMusicales.remove(e);
		
		for (ElementoMusical elemento : elementosMusicales) {
			
			elemento.eliminarElemento(e);
			
		}
		
	}
	
	
	public void cambiarDeLugar(ElementoMusical e, int nuevaPosicion){
		
		elementosMusicales.remove(e);
		elementosMusicales.add(nuevaPosicion, e);
		
	}
	
	public void cambiarDeLugar(int posElementoACambiar, int nuevaPosicion){
		
		ElementoMusical aux = elementosMusicales.elementAt(posElementoACambiar);
		elementosMusicales.removeElementAt(posElementoACambiar);
		elementosMusicales.add(nuevaPosicion, aux);
		
	}
	
	public String getNombre(){
		
		return nombre;
		
	}
	
	public int getDuracion() {
		
		int duracionTotal = 0;
		
		for (ElementoMusical elemento : elementosMusicales) {
			
			duracionTotal += elemento.getDuracion();	
		}
		return duracionTotal;
		
	}

	
	public int getCantPistas(){
		
		int cantPistas = 0;
		
		
		for (ElementoMusical elemento : elementosMusicales) {
			
			cantPistas += elemento.getCantPistas();
			
		}
		return cantPistas;
	}
	
	public String imprimirPistas(){
		
		String resultado= nombre + "\n";
		
		for (ElementoMusical elemento : elementosMusicales) {
			
			resultado += elemento.imprimirPistas() + "\n";
			
		}
		
		return resultado;
		
	}	


	public Vector<Pista> buscarPistas(Condicion c) {
		
		Vector <Pista> resultado = new Vector<Pista>();
		
		for (ElementoMusical e : elementosMusicales) {
			
			resultado.addAll(e.buscarPistas(c));
			
		}
		
		return resultado;
		
	}
	
	
	
}

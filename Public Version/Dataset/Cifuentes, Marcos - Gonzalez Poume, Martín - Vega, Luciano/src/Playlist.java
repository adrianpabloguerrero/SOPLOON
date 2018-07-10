package trabajoEspecialProg2;

import java.util.Collections;
import java.util.Vector;

public class Playlist extends ElementoDeMusica {
	
	private String Nombre;
	private Vector <ElementoDeMusica> playlist = new Vector<ElementoDeMusica>();
	
	
	public Playlist (String _nombre){
		Nombre=_nombre;
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	public void addElemento(ElementoDeMusica c){
		if(!playlist.contains(c))
			playlist.addElement(c);
	}
	
	public void cambiarPosicion(Pista p1, Pista p2){
		int pos1=buscarPosPista(p1);
		int pos2=buscarPosPista(p2);
		
		if ((pos1>0)&&(pos2>0)){
			Collections.swap(playlist,pos1,pos2);
		}
	}
	
	public int buscarPosPista(Pista p){
		int pos=0;
		int invalido=-1;
		
		for (int i=0;i<playlist.size();i++){
			if(p.equals(playlist.elementAt(i))){
				return pos=i;
			}
		}
		return invalido;
	}
	
	public int getDuracionTotal(){
		
		int duracion=0;
		
		for (int i = 0; i < playlist.size(); i++) {
			duracion += playlist.elementAt(i).getDuracionTotal();
		}
		return duracion;
	}
	
	public int getCantidadPistas( ){
		
		int cantPistas=0;
		
		for (int i = 0; i < playlist.size(); i++) {
			cantPistas += playlist.elementAt(i).getCantidadPistas();
		}
		return cantPistas;
	}
	
	public String toString(){
		
		String pista="";
		
		for (int i = 0; i < playlist.size(); i++) {
		pista += playlist.elementAt(i).toString(); 
			
		}
		return pista;
	}
	
	public void removerPista(Pista t){

		playlist.remove(t);
	}

	public Vector<Pista> buscarCondicion(Condicion c1) {
		Vector<Pista> resultado = new Vector<Pista>();

		for (ElementoDeMusica e : playlist) {
			resultado.addAll(e.buscarCondicion(c1));
		}

		return resultado;
	}

	public void eliminarPista(ElementoDeMusica e){

		for (int i = 0; i < playlist.size(); i++) {
			if (playlist.elementAt(i).equals(e)){
				playlist.remove(e);
				i--;
			}
			else { playlist.elementAt(i).eliminarPista(e);

			}


		}
	}
}


package PracticoEspecial;

import java.util.Vector;

public class Playlist extends Elemento {
	
	protected Vector<Elemento> pistas;
	
	public Playlist(String n){
		pistas = new Vector<Elemento>();
		titulo = n;
	}
	
	public void setPista(Elemento p){
		pistas.add(p);
	}
	
	public void deletePista(int key){
		if(key < pistas.size() && key > -1)
		pistas.remove(key);
	}
	
	public int getCantidadElementos(){
		int contador = 0;
		for(Elemento e : pistas){
			contador += e.getCantidadElementos();
		}
		return contador;
	}
	
	public void setOrden(int k, int j){
		if((k < pistas.size() && k > 0) && (j < pistas.size() && j > 0)){
			Elemento aux = pistas.elementAt(k);
			Elemento newpos = pistas.elementAt(j);
			pistas.setElementAt(aux, j);
			pistas.setElementAt(newpos, k);
		}
	}
	
	public int getDuracion(){
		int duracionTotal = 0;
		
		for(int i = 0; i < pistas.size(); i++){
			duracionTotal += pistas.elementAt(i).getDuracion();
		}
		return duracionTotal;
	}
	
	public Vector<Pista> buscar(Condicion c){
		Vector<Pista> resultado = new Vector<Pista>();
		for (Elemento elm : pistas) {
			elm.buscar(c);
			Vector<Pista> aux = new Vector<Pista>();
			aux = elm.buscar(c);
			resultado.addAll(aux);
		}
		return resultado;
		
	}
	
	public String imprimir(){
		String totalPistas = "";
		for(Elemento p: pistas){
			totalPistas += p.imprimir();
		}
		return totalPistas;
	}
	
	public String imprimir(Condicion c){
		String totalPistas = "";
		for (Elemento p : pistas) {
			totalPistas += p.imprimir(c);
		}
		return totalPistas;
	}
	
}

package base;

import java.util.*;

import busquedas.Criterio;

public class PlayListSimple extends Musica {
	
	Vector <Musica> elementos = new Vector<Musica>();

	public PlayListSimple(String nom) {
		nombre = nom;
	}

	public Vector<Musica> getElementos() {
		return elementos;
	}

	public void addElemento (Musica m){
		elementos.add(m);
	}
		
	public void eliminar(Musica m) {
		if (elementos.contains(m)){
			elementos.remove(m);
		}
		for (Musica ite : elementos){
				ite.eliminar(m);
		}
	}
	
	public Vector<Pista> busqueda(Criterio c){
		Vector <Pista> pistas = new Vector<Pista>();
		Vector <Pista> pistas_retornados = new Vector<Pista>();
		for(Enumeration<Musica> E = elementos.elements(); E.hasMoreElements();){
				pistas_retornados = E.nextElement().busqueda(c);
				for(Enumeration<Pista> F = pistas_retornados.elements(); F.hasMoreElements();){
					Pista mi_pista = F.nextElement();
					if(!pistas.contains(mi_pista))	
						pistas.addElement(mi_pista);
				}				
		}
		return pistas;
	}

	public void mostrar(){	
		System.out.println("PlayList: " + this.nombre );
		for(Enumeration<Musica> E = elementos.elements(); E.hasMoreElements();){
			E.nextElement().mostrar();
		}
		System.out.println("----------------------------------------------------------------------------------------------");
	}
	
	public int getDuracion() {
		int acum = 0;
		for (Musica m : getElementos()){
			acum +=m.getDuracion();
		}
		return acum;
	}
	
	public String getInterprete() {
		String salida = "";
		Vector<Musica> aux = getElementos();
		salida = aux.get(0).getInterprete();
		for (Musica m : aux){
			if (!salida.equals(m.getInterprete())){
				salida = "Varios";
				break;
			}
		}
		return salida;
	}
	
	public String getAlbum() {
		String salida = "";
		Vector<Musica> aux = getElementos();
		salida = aux.get(0).getAlbum();
		for (Musica m : aux){
			if (!salida.equals(m.getAlbum())){
				salida = "Varios";
				break;
			}
		}
		return salida;
		
	}
	
	public int getAnio() {
		int mayor = 0;
		for (Musica m : getElementos()){
			if (m.getAnio() > mayor){
				mayor = m.getAnio();
			}
		}
		return mayor;
	}
	
	public String getGenero() {
		String salida = "";
		Vector<String> generos = new Vector<String>();
		Vector<Integer> cantidades = new Vector<Integer>();
		for (Musica m : getElementos()){
			if (generos.contains(m.getGenero())){
				int i = generos.indexOf(m.getGenero());
				cantidades.set(i, cantidades.get(i) + 1);
			}else{
				generos.add(m.getGenero());
				cantidades.add(1);
			}
		}
		int cont = 0;
		for (int i = 0; i < cantidades.size(); i++){
			if (cantidades.get(i) > cont){
				cont = cantidades.get(i);
				salida = generos.get(i);
			}
		}
		return salida;
	}
	
}

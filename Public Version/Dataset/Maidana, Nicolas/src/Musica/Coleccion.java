package Musica;
import java.util.Vector;

import Filtros.Filtro;

public class Coleccion {

	private Vector<Musica> coleccion;

	public Coleccion() {
		coleccion = new Vector<Musica>();
	}

	public void agregarPista(PistaDeAudio pista) {
		if (! coleccion.contains(pista))
			coleccion.addElement(pista);
	}	

	public void eliminarPista(PistaDeAudio pista) {
		coleccion.removeElementAt(pista.getId());
	}

	public int cantElementos() {
		return coleccion.size();
	}

	public Playlist buscar(Filtro f) {
		Playlist aux = new Playlist("Resultado busqueda");
		for (Musica m : coleccion)
			m.busqueda(aux, f);
		return aux;

	}
	
	public Playlist nuevaPlaylist(String titulo){
		Playlist p = new Playlist(titulo);
		coleccion.addElement(p);
		return p;
	}


}

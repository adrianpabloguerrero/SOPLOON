 package Musica;
import java.util.Vector;

import Filtros.Filtro;

public class Playlist extends Musica {

	private Vector<Musica> playlist;

	public Playlist(String titulo) {
		super(titulo);
		playlist = new Vector<Musica>();
	}

	public Playlist(String titulo, Vector<Musica> playlist) {
		super(titulo);
		this.playlist = playlist;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Vector<Musica> getPlaylist() {
		return playlist;
	}

	public void agregarMusica(Musica musica) {
		int i = 0;
		while (i < playlist.size() && playlist.get(i).getTitulo().compareTo(musica.getTitulo()) < 0)
			i++;
		if (i < playlist.size())
			playlist.add(i, musica);
		else
			playlist.addElement(musica);
	}

	public Musica eliminar(PistaDeAudio p) {
		Vector<Musica> aux = new Vector<Musica>();
		for (int i = 0; i < playlist.size(); i++) {
			Musica m = playlist.elementAt(i).eliminar(p);
			if (m != null)
				aux.addElement(m);
		}
		playlist = aux;
		return this;
	}

	private int getPosicion(int id) {
		for (int i = 0; i < playlist.size(); i++) {
			if (((PistaDeAudio) playlist.elementAt(i)).getId() == id)
				return i;
		}
		return -1;
	}

	public void alterarOrden(int id1, int id2) {
		Musica p1 = (PistaDeAudio) playlist.elementAt(getPosicion(id1));
		Musica p2 = (PistaDeAudio) playlist.elementAt(getPosicion(id2));
		playlist.setElementAt(p1, getPosicion(id2));
		playlist.setElementAt(p2, getPosicion(id1));
	}

	public int getDuracion() {
		int duracionParcial = 0;
		for (Musica m : playlist) {
			duracionParcial += m.getDuracion();
		}
		return duracionParcial;
	}

	public int getCantPistas() {
		int cantPistasParcial = 0;
		for (Musica m : playlist) {
			cantPistasParcial += m.getCantPistas();
		}
		return cantPistasParcial;
	}

	
	public String toString (){
		String aux = "";
		for (Musica m: playlist)
			aux += m.toString();
		return aux;
	}

	public void busqueda(Playlist aux, Filtro f) {
		for (Musica m : playlist)
			m.busqueda(aux, f);
	}
	
	public boolean pertenece (Musica m){
		return playlist.contains(m);
	}
	
}

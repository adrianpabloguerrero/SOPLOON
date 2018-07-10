import java.util.Vector;

public class Playlist extends Contenedor{

	Vector<Contenedor> playlist;

	//Constructor:

	public Playlist(String nombre){

		super(nombre);
		playlist = new Vector<Contenedor>();

	}

	//Metodos:

	public void addElemento(Contenedor e){

		playlist.add(e);
	}

	public double getDuracion() {
		double duracion = 0;
		for (Contenedor elemento : playlist) {
			duracion += elemento.getDuracion();
		}
		return duracion;
	}

	public int getElementos() {
		int elementos = 0;
		for (Contenedor elemento : playlist) {
			elementos += elemento.getElementos();
		}
		return elementos;
	}

	public void imprimir() {
		for (Contenedor elemento : playlist) {
			elemento.imprimir();
		}
	}

	public void borrarElemento(Contenedor c) {
		for (Contenedor elemento : playlist) {
			elemento.borrarElemento(c);
		}
		playlist.remove(c);
	}
}
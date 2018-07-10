package sistema;

import java.util.Collections;
import java.util.Vector;

public class Lista extends ElementoMusical {

	Vector<ElementoMusical> lista;

	public Lista (String nomb){
		super(nomb);
		lista = new Vector <ElementoMusical>();
	}

	// Agrego un elemento a esta playlist (Pista o Playlist)
	public void add(ElementoMusical f) {
		lista.addElement(f);
	}

	// Obtengo la duracion de una pista o playlist especifica
	public int getDuracion(ElementoMusical f){
		return f.getDuracion();
	}

	// Cambio la posicion de los elementos de la playlist
	public void cambiarPosicion(int pos1, int pos2) {
		if( (pos1 < lista.size()) && (pos2 < lista.size()) && (pos1 >= 0) && (pos2 >= 0) )
			Collections.swap(lista, pos1, pos2);
	}

	// Obtengo la posicion de esta playlist
	public int getDuracion(){
		int dur=0;
		for (ElementoMusical f : lista) {
			dur += f.getDuracion();
		}
		return dur;
	}

	// Realizo una busqueda de pistas utilizando un criterio determinado
	public Vector<Pista> buscador(Busqueda b) {
		Vector<Pista> resultado = new Vector<Pista>();
		for (ElementoMusical f : lista){
			resultado.addAll(f.buscador(b));
		}
		return resultado;
	}

	// Obtengo la cantidad de elementos en esta playlist.
	public int getCantidad() {
		int cant=0;
		for (ElementoMusical f : lista) {
			cant += f.getCantidad();
		}
		return cant;
	}

	// Obtengo un string con todos los elementos de esta playlist
	public String getResumen() {
		String resumen = nombre.toUpperCase() + "\n";
		for (ElementoMusical f : lista) {
			resumen += f.getResumen() + "\n";
		}
		return resumen;
	}

	// Obtengo un string con elementos de una Playlist o Pista especifica
	public String getResumen(ElementoMusical e) {
		return e.getResumen();
	}

	// Elimino una pista de esta playlist
	public Pista eliminarPista(Pista pista) {
        for (int i = 0; i < lista.size(); i++) {
            Pista nueva = lista.get(i).eliminarPista(pista);
            if(nueva != null && nueva.equals(pista)) {
                lista.remove(i);
            }
        }
        return null;
    }

    // Elimino una pista de una playlist especifica
	public void eliminarPista(Pista p, Lista pl) {
		pl.eliminarPista(p);
	}



}
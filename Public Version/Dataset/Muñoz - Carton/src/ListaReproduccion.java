package practicoEspecial;

import java.util.Vector;

public class ListaReproduccion extends ElementoMusica {
	private Vector<ElementoMusica> elements;

	public ListaReproduccion(String _nombre) {
		super(_nombre);
		elements = new Vector<ElementoMusica>();
	}

	public void addElement(ElementoMusica p) {
		if (!elements.contains(p)) {
			elements.addElement(p);
		}
	}

	public String toString() {
		String info = "Playlist: " + nombre;
		for (int i = 0; i < elements.size(); i++) {
			ElementoMusica els = elements.elementAt(i);
			info = info + "\n" + els.toString();
		}
		return info;
	}

	public String getCantElementos() {
		String dato;
		dato = "Cantidad de pistas de la lista " + nombre + ": " + elements.size();
		return dato;

	}

	public void eliminar(ElementoMusica p) {
		for (int i = 0; i < elements.size(); i++) {
			ElementoMusica pAux = elements.elementAt(i);
			if ((pAux).equals(p)) {
				elements.remove(pAux);
			}
		}
	}

	public int getDuracion() {
		int cont = 0;
		for (int i = 0; i < elements.size(); i++) {
			if (!elements.isEmpty()) {
				cont += elements.elementAt(i).getDuracion();
			}
		}
		return cont;

	}

	public Vector<ElementoMusica> buscar(Condicion s) {
		Vector<ElementoMusica> resultado = new Vector<ElementoMusica>();
		for (int i = 0; i < elements.size(); i++) {
			ElementoMusica els = (ElementoMusica) elements.elementAt(i);
			Vector<ElementoMusica> auxiliar = els.buscar(s);
			resultado.addAll(auxiliar);
		}
		return resultado;
	}

}

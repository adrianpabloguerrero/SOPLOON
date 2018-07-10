import java.util.*;

public class Playlist extends Elemento {

	private ArrayList<Elemento> pistas;

	public Playlist (String nombre) {
		super(nombre);
		this.pistas = new ArrayList<Elemento>();
	}

	public void printElemento () {
		System.out.println("Lista de reproduccion." + this.getNombre());
		for (int i = 0; i < this.pistas.size(); i++) {
			pistas.get(i).printElemento();
		}
	}

	public void addPista (Elemento elemento) {
		this.pistas.add(elemento);
	}

	public void removePista (Elemento elemento) {
		System.out.print("La pista  " + elemento.nombre + " ha sido eliminada...");
		this.pistas.remove(elemento);
	}


	public String getNombre () {
		return nombre;
	}

	public int getDuracion () {
		int duracion = 0;
		for (int i = 0; i < this.pistas.size(); i++) {
			duracion+= pistas.get(i).getDuracion();
		}
		return duracion;
	}

	public int getCantidadPistas () {
		int cantidadPistas = 0;
		for (int i = 0; i < this.pistas.size(); i++) {
			cantidadPistas += pistas.get(i).getCantidadPistas();
		}
		return cantidadPistas;
	}

	public ArrayList<Elemento> getPistas () {
		return pistas;
	}

	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

}

import java.util.*;

public class PlayList extends ElementoMusical {

	private ArrayList<ElementoMusical> pistas;

	public PlayList (String nombre) {
		super(nombre);
		this.pistas = new ArrayList<ElementoMusical>();
	}

	public void printElemento () {
		System.out.println("Lista de reproduccion." + this.getNombre());
		for (int i = 0; i < this.pistas.size(); i++) {
			pistas.get(i).printElemento();
		}
	}

	public void addPista (ElementoMusical elemento) {
		this.pistas.add(elemento);
	}

	public void removePista (ElementoMusical elemento) {
		System.out.print("La pista " + elemento.nombre + " fue removida de la lista.");
		this.pistas.remove(elemento);			
	}

	/* Get functions */
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

	public ArrayList<ElementoMusical> getPistas () {
		return pistas;
	}

	/* Set functions */
	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

}

import java.util.ArrayList;

public class Coleccion {

	private ArrayList<Pista> coleccionCompleta;

	public Coleccion () {
		this.coleccionCompleta = new ArrayList<Pista>();
	}

	public void addPista (Pista pista) {  
		this.coleccionCompleta.add(pista);
	}

	public void removePista (Pista pista) {
		this.coleccionCompleta.remove(pista);
	}

	public ArrayList<Pista> resultadoBusqueda (Condicion condicion) {
		ArrayList<Pista> resultado = new ArrayList<Pista>();
		for (int i = 0; i < coleccionCompleta.size(); i++) {
			if (condicion.satisfaceCondicion(coleccionCompleta.get(i))) {
				resultado.add(coleccionCompleta.get(i));
			}
		}
		return resultado;
	}

}
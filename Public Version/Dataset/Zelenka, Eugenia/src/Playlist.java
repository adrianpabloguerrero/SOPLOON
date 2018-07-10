import java.util.Vector;

public class Playlist extends Elemento{
	Vector<Elemento> elems;

	public Playlist(String nomb) {
		super(nomb);
		elems = new Vector<>();
	}

	public Vector<Elemento> getElems() {
		return elems;
		//
	}

	public int getDuracion(){
		int suma = 0;
		for (int i = 0; i < elems.size(); i++) {
			Elemento e1 = (Elemento)elems.elementAt(i);
			suma = suma + e1.getDuracion();
		}
		return suma;
	}

	public int getCantPista(){
		int suma = 0;
		for (int i = 0; i < elems.size(); i++) {
			Elemento e1 = (Elemento)elems.elementAt(i);
			suma = suma + e1.getCantPista();
		}
		return suma;
	}

	public void setElems(Vector<Elemento> elems) {
		//
	}

	public void add(Elemento e1) {
		elems.add(e1);
	}

	public void delete(Pista p){
		for (int i = 0; i < elems.size(); i++) {
			Elemento e = (Elemento)elems.elementAt(i);
			if(e.equals(p)){
				elems.removeElementAt(i);
				i--;
			}
			else {
				elems.elementAt(i).delete(p);
			}
		}
	}

	public String toString(){
		String datos = " Nombre: " + this.nombre;
		for (int i = 0; i < elems.size(); i++) {
			Elemento e = (Elemento)elems.elementAt(i);
			datos = datos + "\n" + e.toString();
		}
		return datos;
	}

	public void modificaOrden(int posDestino, String nomb){
		if(posDestino < elems.size()){
			int i = 0;
			while((i < elems.size()) && elems.elementAt(i).nombre != nomb){
				i++;
			}
			if(i != elems.size()){
				Elemento eAux = elems.elementAt(i);
				elems.removeElementAt(i);
				elems.insertElementAt(eAux, posDestino);
			}
		}
	}

	public Vector<Pista> buscarCondicion(Condicion c1) {
		Vector<Pista> resultado = new Vector<Pista>();

		for (Elemento e : elems) {
			resultado.addAll(e.buscarCondicion(c1));
		}

		return resultado;
	}
}


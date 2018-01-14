import java.util.Collections;
import java.util.Vector;

public class Playlist extends PLFE {

	String nombre;
	Vector <PLFE> elem;

	public Playlist(String n){
		nombre = n;
		elem = new Vector<PLFE>();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)return true;
		
		if(obj instanceof Playlist){
			return nombre.equals(((Playlist)obj).getNombre());
		}
		return false;
	}
	
	public void addElem(PLFE p){
		elem.addElement(p);
	}
	
	private int buscaPosElem(PLFE p){
		for (int i = 0; i < elem.size(); i++) {
			
			PLFE aux = elem.elementAt(i);
			
			if (aux.equals(p)){
				return i;
			}
		}
		return -1;
	}

	public void deleteElem(PLFE r){
		elem.remove(r);
	}

	public int getDuracion() {
		int cuentaSeg = 0;
		for (int i = 0; i < elem.size(); i++) {
			cuentaSeg = cuentaSeg + elem.elementAt(i).getDuracion();
		}
		return cuentaSeg;
	}

	public int cantPistas() {
		int cantPistas = 0;
		for (int i = 0; i < elem.size(); i++) {
			cantPistas = cantPistas + elem.elementAt(i).cantPistas();
		}
		return cantPistas;
	}
	
	public String toString(){
		String mensaje = "Playlist: "+nombre +"\n";
		for (int i = 0; i < elem.size(); i++) {
			mensaje = mensaje + elem.elementAt(i).toString();
		}
		return mensaje;
	}
	
	
	public void modificarOrden(PLFE p,int pos){
		
		int pos1 = buscaPosElem(p);
				
		System.out.println(pos1);
		Collections.swap(elem, pos1, pos);
		
	}
	

	@Override
	public Vector<PLFE> buscar(Condicion c) {
		Vector<PLFE> aux = new Vector<PLFE>();

		for (int i = 0; i <elem.size(); i++) {
			Vector<PLFE> elemento = elem.elementAt(i).buscar(c);
			aux.addAll(elemento);
		}
		return aux;
	}
	
	//get and set
	
	public void setNombre(String nom){
		nombre = nom;
	}
	
	public String getNombre(){
		return nombre;
	}
	

}

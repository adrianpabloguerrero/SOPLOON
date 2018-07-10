

import java.util.Vector;

public class ListaReproduccion extends Elemento{
	private String nombre;
	private Vector<Elemento> lista;
	
	public ListaReproduccion(String nom){
		nombre=nom;
		lista=new Vector<Elemento>();
	}
	
	public void add(Elemento x){
		lista.addElement(x);
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public boolean Existe(Elemento elem){
		boolean aux=false;
		int i=0;
		String nombre;
		while ((!aux)&i<lista.size()){
			nombre=lista.elementAt(i).getNombre();
			if	(nombre==elem.getNombre())
				aux=true;
			i++;
		}
		return false;
	}
	
	public int getPosicion(Elemento elem){
		if (Existe(elem)){
			int i=0;
			String nomb;
			while (i<lista.size()){
				nomb=lista.elementAt(i).getNombre();
				if (nomb==elem.getNombre())
					return i;
				i++;
				}
		}				
		return -1;
	}	
	
	public int getDuracion(){//suma la duracion de toda la Playlist
		int duracion=0;
		for(int i=0; i<lista.size();i++){
			duracion=duracion+((Elemento)lista.elementAt(i)).getDuracion();
			}
		return duracion;			
	}	
	
	public int getCantidad(){//cuenta la cantidad de elementos de la Playlist
		int cantidad=0;
		for (int i=0;i<lista.size();i++){
			cantidad=cantidad+((Elemento)lista.elementAt(i)).getCantidad();
			}
		return cantidad;
}	
	
	public void imprimir(){		
		for(int i=0; i<lista.size(); i++){
			lista.elementAt(i).imprimir();			
		}
	}
	
	public void intercambiar(int x, int z){		
		lista.add(z, lista.elementAt(x));
		lista.remove(x+1);
		lista.add(x, lista.elementAt(z+1));
		lista.remove(z+1);			
	}
	
	
	public void eliminar(int x){
		lista.removeElementAt(x);
	}
	
	public Vector<Elemento> buscarCondicion(Condicion c){
		Vector<Elemento> resultado=new Vector<Elemento>();
		for (int i = 0; i < lista.size(); i++) {
			Elemento el1=lista.elementAt(i);
			Vector<Elemento> resAux=el1.buscarCondicion(c);
			resultado.addAll(resAux);			
		}
		return resultado;
			
		}

		
	
	
	
}

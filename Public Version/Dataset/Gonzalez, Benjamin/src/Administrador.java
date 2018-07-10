package Lista_de_Musica;
import java.util.Vector;

public class Administrador {
	private Vector<Elemento> musica;
	
	public Administrador(){
		musica = new Vector<Elemento>();
		
	}
	public void mostrarListado(ListaReproduccion l){
		for(int i=0;i<l.getElementos().size(); i++){
			System.out.println(l.getElementos().elementAt(i).getElemento(i));
		}
	}
	
	public void agregarMusica(Elemento e){
		musica.add(e);
	}
	
	public void eliminar(Pista p){
		for(int i=0; i<musica.size();i++){
			((ListaReproduccion) musica.elementAt(i)).eliminar(p);
		}
	}
	public void buscar(Condicion c){
		for(int j=0; j<musica.size();j++){
			if(musica.elementAt(j).buscar(c).size() == 0)
				System.out.println(" y no hay resultados de la busqueda.");
			else {
				System.out.println("pistas cuya condiciones:  "+c+musica.elementAt(j).getNombre());
				for(int i=0; i<musica.elementAt(i).buscar(c).size();i++){
					System.out.println(musica.elementAt(i).buscar(c).elementAt(i).getNombre());
				}
			}	
		}		
		System.out.println("");
		
	}
	
	/*
	 * cree esta clase para poder mostrar el listado.
	 * 
	 * mas adelate quiero poder configuar todo desde aca.
	 */
}

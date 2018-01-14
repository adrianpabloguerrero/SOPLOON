import java.util.Vector;

public abstract class Elemento {
	String nombre;
	
	public Elemento(String nomb) {
		nombre = nomb;
	}
	
	public abstract void add(Elemento e);
	
	public abstract int getDuracion();
	
	public abstract int getCantPista();
	
	public String getNombre(){
		return nombre;
	}
	
	public abstract Vector<Pista> buscarCondicion(Condicion c1);
	


	public abstract void delete(Pista p);
}

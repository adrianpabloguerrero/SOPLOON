
public class CondNombre extends Condicion {
	private String nombre;
	
	public CondNombre(String val){
		nombre=val;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public boolean cumple(Elemento e){
		return ((Cancion)e).getNombre().contains(nombre);
	}

}

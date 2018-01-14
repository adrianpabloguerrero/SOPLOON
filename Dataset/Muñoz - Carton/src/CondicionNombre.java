package practicoEspecial;

public class CondicionNombre extends Condicion {
	
	private String nombre;
	public CondicionNombre(String n){
		nombre=n.toLowerCase();
	}
	public boolean cumple(Pista p) {
		return (p.getNombre().toLowerCase().contains(nombre));
	}

}

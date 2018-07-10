public abstract class Elemento {
	
	protected String nombre;

	public Elemento(String nombre){
		this.nombre = nombre;
	}

	public abstract String getNombre();
	public abstract int getDuracion();
	public abstract int getCantidadPistas();
	public abstract void printElemento();

}
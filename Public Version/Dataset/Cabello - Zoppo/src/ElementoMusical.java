public abstract class ElementoMusical {
	
	protected String nombre;

	public ElementoMusical(String nombre){
		this.nombre = nombre;
	}

	public abstract String getNombre();
	public abstract int getDuracion();
	public abstract int getCantidadPistas();
	public abstract void printElemento();

}

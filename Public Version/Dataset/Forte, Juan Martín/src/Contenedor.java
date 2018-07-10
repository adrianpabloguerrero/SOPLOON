public abstract class Contenedor extends ReproductorMusical{

	private String nombre;

	public Contenedor(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre(){
		return nombre;
	}

	public abstract double getDuracion();
	public abstract int getElementos();
	public abstract void imprimir();
	public abstract void borrarElemento(Contenedor c);

}
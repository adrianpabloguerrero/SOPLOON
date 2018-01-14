package base;
import java.util.*;
import busquedas.Criterio;

public abstract class Musica {
	String nombre;
	
	public abstract String getInterprete();
	public abstract int getDuracion();
	public abstract int getAnio();
	public abstract String getGenero();
	public abstract String getAlbum();
	public abstract void mostrar();
	public abstract Vector<Pista> busqueda(Criterio c);
	public abstract void eliminar(Musica m);
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void mostrarDuracion(){
		System.out.println("Duracion de " + this.nombre + ": " + this.getDuracion());
	}
}

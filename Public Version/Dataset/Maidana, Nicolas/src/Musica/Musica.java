package Musica;
import Filtros.Filtro;

public abstract class Musica {
	
	protected String titulo;
	
	public Musica(String titulo){
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public abstract int getDuracion();
	
	public abstract int getCantPistas();
		
	public abstract Musica eliminar(PistaDeAudio p);
	
	public abstract void busqueda (Playlist aux, Filtro f);

}

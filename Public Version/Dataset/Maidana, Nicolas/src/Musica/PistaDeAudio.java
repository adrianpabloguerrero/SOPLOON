package Musica;

import Filtros.Filtro;

public class PistaDeAudio extends Musica {

	private int id;
	private int duracion;
	private String interprete;
	private String album;
	private int anio;
	private String genero;
	private String comentarios;

	public PistaDeAudio(int id, String titulo, int duracion, String interprete, String album, int anio, String genero,
			String comentarios) {
		super(titulo);
		this.id = id;
		this.duracion = duracion;
		this.interprete = interprete;
		this.album = album;
		this.anio = anio;
		this.genero = genero;
		this.comentarios = comentarios;
	}

	public int getId() {
		return id;
	}

	public int getDuracion() {
		return duracion;
	}
	
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getInterprete() {
		return interprete;
	}
	
	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}

	public String getAlbum() {
		return album;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}

	public int getAnio() {
		return anio;
	}
	
	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public int getCantPistas() {
		return 1;
	}
	
	public String toString() {
		return	 "Titulo: " + getTitulo() + "\n"  + "Interprete: " + getInterprete() + "\n" + "Album: " + getAlbum() + "\n" + "Anio: " + getAnio() + "\n"  + "\n";
	}

	public Musica eliminar(PistaDeAudio p) {
		if (id == p.getId())
			return null;
		else
			return this;
	}

	public void busqueda(Playlist aux, Filtro f) {
		if (f.cumple(this) && ! aux.pertenece(this))
			aux.agregarMusica(this);
	}

}

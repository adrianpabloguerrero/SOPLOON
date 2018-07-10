

import java.util.Vector;

public class Pista extends ElementoMusical {
	private int id;
	private String titulo;
	private int duracion;
	private String artista;
	private String album;
	private int anio;
	private String genero;
	private String comentarios;
	
	
	public Pista(int id, String titulo, int duracion, String artista, String album, int anio, String genero, String comentarios) {
		this.id = id;
		this.titulo = titulo;
		this.duracion = duracion;
		this.artista = artista;
		this.album = album;
		this.anio = anio;
		this.genero = genero;
		this.comentarios = comentarios;
	}
	
	public Vector<ElementoMusical> coincide(Condicion c1){
		Vector<ElementoMusical> elementosQueCoincide = new Vector<ElementoMusical>();
		if (c1.cumple(this)){
			elementosQueCoincide.add(this);
		}
		return elementosQueCoincide;
	}
	
	public int getDuracion(){
		return duracion;
	}
	
	public String getAutor(){
		return artista;
	}
	
	public String getGenero(){
		return genero;
	}

	public String getTitulo(){
		return titulo;
	}
	
	public int getAnio(){
		return anio;
	}
	
 	public int cantidadElementos(){
		return 1;
	}
	
	public String toString(){
		 String imprimir = ("Id: "+id+". Titulo: "+titulo+". Artista/Interprete: "+artista+". Genero: "+genero+". Año: "+anio+". Durcion: "+duracion+"\n");
		return imprimir;
	}
	

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

}

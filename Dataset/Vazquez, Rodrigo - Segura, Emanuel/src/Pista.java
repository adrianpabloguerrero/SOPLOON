import java.util.Vector;

public class Pista extends ElementoMusical{
	private int id;
	private String titulo;
	private int duracion;
	private String artista;
	private String album;
	private int anio;
	private String genero;
	private String comentarios;
	private static int contador = 1;
	
	public Pista(String titulo, int duracion, String artista, String album, int anio, String genero,
			String comentarios) {
		this.id = contador++;
		this.titulo = titulo;
		this.duracion = duracion;
		this.artista = artista;
		this.album = album;
		this.anio = anio;
		this.genero = genero;
		this.comentarios = comentarios;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public int getId(){
		return id;
	}
	
	public int getDuracion(){
		return duracion;
	}
	
	public String getTitulo(){
		return titulo;
	}
	
	public String getArtista(){
		return artista;
	}
	
	public String getGenero(){
		return genero;
	}
	
	public String getAlbum(){
		return album;
	}
	
	public int getAnio(){
		return anio;
	}
	
	public String toString (){
		String mensaje = id+" - "+titulo+" - "+artista+" - "+album+" ("+genero+", "+anio+") - "+duracion+"\n";
		return mensaje;
	}
	
	public int getCantElementos(){
		return 1;
	}
	
	public Vector<ElementoMusical> devolverElemFiltrados(Condicion condicion){
		Vector<ElementoMusical> elementosFiltrados = new Vector<ElementoMusical>();
		if (condicion.cumple(this)){
			elementosFiltrados.add(this);
		}
		return elementosFiltrados;
	}

	
}
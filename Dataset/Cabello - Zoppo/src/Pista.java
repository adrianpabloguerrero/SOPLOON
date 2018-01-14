import java.util.*;

public class Pista extends ElementoMusical {

	private int ID;
	private int duracion;
	private String artista;
	private String album;
	private int a�o;
	private String genero;
	private ArrayList<String> comentarios;
	
	public Pista (int ID,String nombre, int duracion, String artista, String album, int a�o, String genero) {
		super(nombre);
		this.ID = ID;
		this.duracion = duracion;
		this.artista = artista;
		this.album = album;
		this.a�o = a�o;
		this.genero = genero;
		this.comentarios = new ArrayList<String>();
	}
	
	public void printElemento () {
		System.out.println(this.ID + " - " + this.nombre + " - " + this.artista + " - " + this.album + " ( " + this.genero + " - " + this.a�o + " ) " + this.duracion);
	}

	/* Get functions */
	public int getID () {
		return ID;
	}
	
	public String getArtista () {
		return artista;
	}
	
	public String getAlbum () {
		return album;
	}
	
	public int getA�o () {
		return a�o;
	}
	
	public String getGenero () {
		return genero;
	}
	
	public String getNombre () {
		return nombre;
	}

	public ArrayList<String> getComentarios () {
		return comentarios;
	}

	public int getDuracion () {
		return duracion;
	}

	public int getCantidadPistas () {
		return 1;
	}
	
	/* Set functions */
	public void setID (int ID) {
		this.ID = ID;
	}
	
	public void setDuracion (int duracion) {
		this.duracion = duracion;
	}
	
	public void setArtista (String artista) {
		this.artista = artista;
	}
	
	public void setAlbum (String album) {
		this.album = album;
	}
	
	public void setA�o (int a�o) {
		this.a�o = a�o;
	}
	
	public void setGenero (String genero) {
		this.genero = genero;
	}
	
	public void setComentario (String comentario) {
		this.comentarios.add(comentario);
	}
}

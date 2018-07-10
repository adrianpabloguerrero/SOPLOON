import java.util.*;

public class Pista extends Elemento {

	private int ID;
	private int duracion;
	private String artista;
	private String album;
	private int año;
	private String genero;
	private ArrayList<String> comentarios;

	public Pista (int ID,String nombre, int duracion, String artista, String album, int año, String genero) {
		super(nombre);
		this.ID = ID;
		this.duracion = duracion;
		this.artista = artista;
		this.album = album;
		this.año = año;
		this.genero = genero;
		this.comentarios = new ArrayList<String>();
	}

	public void printElemento () {
		System.out.println(this.ID + " - " + this.nombre + " - " + this.artista + " - " + this.album + " ( " + this.genero + " - " + this.año + " ) " + this.duracion);
	}

	/* Gets */
	public int getID () {
		return ID;
	}

	public String getArtista () {
		return artista;
	}

	public String getAlbum () {
		return album;
	}

	public int getAño () {
		return año;
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

	public void setAño (int año) {
		this.año = año;
	}

	public void setGenero (String genero) {
		this.genero = genero;
	}

	public void setComentario (String comentario) {
		this.comentarios.add(comentario);
	}
}
